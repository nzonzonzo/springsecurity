package com.changgou.goods.service.impl;

import com.changgou.core.service.impl.CoreServiceImpl;
import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl extends CoreServiceImpl<Brand> implements BrandService {

    private BrandMapper brandMapper;

    @Autowired
    public BrandServiceImpl(BrandMapper brandMapper) {
        super(brandMapper, Brand.class);
//        this.brandMapper = brandMapper;
    }

//    @Autowired
//    private BrandMapper brandMapper;
//
//    @Override
//    public List<Brand> findAll() {
//        List<Brand> brands = brandMapper.selectAll();
//        return brands;
//    }
//
//    @Override
//    public void insertBrand(Brand brand) {
//        brandMapper.insertSelective(brand);
//    }
//
//    @Override
//    public Brand findById(Integer id) {
//        return brandMapper.selectByPrimaryKey(id);
//    }
//
//    @Override
//    public void updateBrand(Brand brand) {
//        brandMapper.updateByPrimaryKeySelective(brand);
//    }
//
//    @Override
//    public void deleteBrand(Integer id) {
//        brandMapper.deleteByPrimaryKey(id);
//    }
//
//    @Override
//    public List<Brand> search(Brand brand) {
//        /**
//         * select * from tb_brand where name like ? and letter = ?
//         * select * from tb_brand where name like ?
//         * select * from tb_brand where letter = ?
//         * select * from tb_brand
//         */
//        Example example = new Example(Brand.class);
//
//        if (brand != null) {
//            searchJudge(brand, example);
//        }
//
//        List<Brand> brands = brandMapper.selectByExample(example);
//
//        return brands;
//    }
//
//    @Override
//    public PageInfo<Brand> page(int page, int size) {
//        PageHelper.startPage(page, size);
//
//        List<Brand> brands = brandMapper.selectAll();
//
//        PageInfo<Brand> pageInfo = new PageInfo<>(brands);
//
//        return pageInfo;
//    }
//
//    @Override
//    public PageInfo<Brand> searchPage(Brand brand, int page, int size) {
//        PageHelper.startPage(page, size);
//        Example example = new Example(Brand.class);
//
//        if (brand!=null){
//            searchJudge(brand,example);
//        }
//
//        List<Brand> brands = brandMapper.selectByExample(example);
//
//        PageInfo<Brand> pageInfo = new PageInfo<>(brands);
//        return pageInfo;
//    }
//
//
//    /**
//     * 判断查询条件的方法
//     * @param brand
//     * @param example
//     */
//    private void searchJudge(Brand brand, Example example) {
//        Example.Criteria criteria = example.createCriteria();
//
//        if (StringUtils.isNotBlank(brand.getName())) {
//            criteria.andLike("name", "%" + brand.getName() + "%");
//        }
//
//        if (StringUtils.isNotBlank(brand.getLetter())) {
//            criteria.andEqualTo("letter", brand.getLetter());
//        }
//    }

}
