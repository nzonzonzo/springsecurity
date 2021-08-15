package com.changgou.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.changgou.goods.feign.SearchSkuFeign;
import com.changgou.goods.pojo.Sku;
import com.changgou.search.dao.SearchSkuEsMapper;
import com.changgou.search.pojo.SkuInfo;
import com.changgou.search.service.SearchService;
import entry.PageResult;
import entry.Result;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.search.MatchQuery;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchSkuFeign searchSkuFeign;

    @Autowired
    private SearchSkuEsMapper searchSkuEsMapper;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Override
    public void importFromDbToEs() {
        //1.通过feign调用从goods微服务里查询到Sku列表数据
        Result<List<Sku>> listResult = searchSkuFeign.searchSku("1");
        List<Sku> skuList = listResult.getData();

//        2.将数据插入到ES中
        String json = JSON.toJSONString(skuList);
        List<SkuInfo> skuInfos = JSON.parseArray(json, SkuInfo.class);
        for (SkuInfo skuInfo : skuInfos) {
            String spec = skuInfo.getSpec();
            Map map = JSON.parseObject(spec, Map.class);
            skuInfo.setSpecMap(map);
        }
        searchSkuEsMapper.saveAll(skuInfos);
    }

    @Override
    public Map search(Map<String, String> searchMap) {
        String keyWords = searchMap.get("keyWords");
        if (StringUtils.isEmpty(keyWords)){
            keyWords = "华为";
        }
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.matchQuery("name", keyWords));
        //品牌聚合
        String brandAggName = "brandAgg";
        queryBuilder.addAggregation(AggregationBuilders.terms(brandAggName).field("brandName").size(100));
        //分类聚合
        String categoryAggName = "categoryAgg";
        queryBuilder.addAggregation(AggregationBuilders.terms(categoryAggName).field("categoryName").size(100));
       // queryBuilder.withPageable(PageRequest.of(0, 20));
        AggregatedPage<SkuInfo> skuInfos = elasticsearchTemplate.queryForPage(queryBuilder.build(), SkuInfo.class);

        long totalElements = skuInfos.getTotalElements();
        int totalPages = skuInfos.getTotalPages();
        List<SkuInfo> content = skuInfos.getContent();
        //获取品牌聚合数据
        List brandList = getAggList(skuInfos, "brandAgg");
        //获取分类聚合数据
        List categoryList = getAggList(skuInfos, "categoryAgg");
//        System.out.println("totalPages = " + /totalPages);
//        System.out.println("totalElements = " + totalElements);
        HashMap<String, Object> map = new HashMap<>();
        map.put("totalElements", totalElements);
        map.put("totalPages", totalPages);
        map.put("content", content);
        map.put("brandList", brandList);
        map.put("categoryList", categoryList);
        return map;
    }

    private List getAggList(AggregatedPage<SkuInfo> skuInfos,String aggName) {
        StringTerms stringTerms =(StringTerms) skuInfos.getAggregation(aggName);

        List<StringTerms.Bucket> buckets = stringTerms.getBuckets();

        ArrayList<Object> list = new ArrayList<>();
        for (StringTerms.Bucket bucket : buckets) {
            String key = bucket.getKeyAsString();
            list.add(key);
        }
        return list;
    }
}
