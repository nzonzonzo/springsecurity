package com.changgou.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.changgou.core.service.impl.CoreServiceImpl;
import com.changgou.goods.dao.CategoryMapper;
import com.changgou.goods.dao.SkuMapper;
import com.changgou.goods.dao.SpuMapper;
import com.changgou.goods.pojo.Category;
import com.changgou.goods.pojo.Goods;
import com.changgou.goods.pojo.Sku;
import com.changgou.goods.pojo.Spu;
import com.changgou.goods.service.SpuService;
import entry.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/****
 * @Author:admin
 * @Description:Spu业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class SpuServiceImpl extends CoreServiceImpl<Spu> implements SpuService {

    private SpuMapper spuMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    public SpuServiceImpl(SpuMapper spuMapper) {
        super(spuMapper, Spu.class);
        this.spuMapper = spuMapper;
    }

    /**
     * 保存Goods
     * @param goods
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveGoods(Goods goods) {
        //保存Spu
        Spu spu = goods.getSpu();
        spu.setId(idWorker.nextId());
        spuMapper.insertSelective(spu);
        //保存Sku
        addSkuList(goods, spu);
    }

    /**
     * 抽取的添加Sku的方法
     * @param goods
     * @param spu
     */
    private void addSkuList(Goods goods, Spu spu) {
        Long id = spu.getId();
        Integer category3Id = spu.getCategory3Id();
        Category category = categoryMapper.selectByPrimaryKey(category3Id);
        List<Sku> skuList = goods.getSkuList();
        for (Sku sku : skuList) {
            sku.setId(idWorker.nextId());
            String specJson = sku.getSpec();
            Map<String, String> map = JSON.parseObject(specJson, Map.class);
            String name = sku.getName();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String value = entry.getValue();
                name += " " + value;
            }
            sku.setName(name);
            sku.setCreateTime(new Date());
            sku.setUpdateTime(sku.getCreateTime());
            sku.setSpuId(id);
            sku.setCategoryId(category3Id);
            sku.setCategoryName(category.getName());
            sku.setStatus("1");
            skuMapper.insertSelective(sku);
        }
    }

    @Override
    public Goods findGoodsById(Long id) {
        //根据id查询spu
        Spu spu = spuMapper.selectByPrimaryKey(id);
        //查询sku
        Sku sku = new Sku();
        sku.setSpuId(id);
        List<Sku> skuList = skuMapper.select(sku);
        return new Goods(spu,skuList);
    }

    /**
     * 更新Goods
     * @param goods
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateGoods(Goods goods) {
        //更新Spu
        Spu spu = goods.getSpu();
        spuMapper.updateByPrimaryKeySelective(spu);
        //更新sku
        Sku sku = new Sku();
        sku.setSpuId(spu.getId());
        //先删除所有以前的sku数据
        skuMapper.delete(sku);
        //再添加sku
        addSkuList(goods,spu);
    }
}
