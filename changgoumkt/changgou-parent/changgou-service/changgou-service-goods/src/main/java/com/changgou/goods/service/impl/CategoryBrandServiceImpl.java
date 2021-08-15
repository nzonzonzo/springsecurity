package com.changgou.goods.service.impl;

import com.changgou.core.service.impl.CoreServiceImpl;
import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.dao.CategoryBrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.pojo.CategoryBrand;
import com.changgou.goods.service.CategoryBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/****
 * @Author:admin
 * @Description:CategoryBrand业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class CategoryBrandServiceImpl extends CoreServiceImpl<CategoryBrand> implements CategoryBrandService {

    private CategoryBrandMapper categoryBrandMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    public CategoryBrandServiceImpl(CategoryBrandMapper categoryBrandMapper) {
        super(categoryBrandMapper, CategoryBrand.class);
        this.categoryBrandMapper = categoryBrandMapper;
    }

    @Override
    public List<Brand> findBrandByCid(Integer id) {
//        CategoryBrand categoryBrand = new CategoryBrand();
//        categoryBrand.setCategoryId(id);
//        List<CategoryBrand> list = categoryBrandMapper.select(categoryBrand);
//        ArrayList<Brand> brands = new ArrayList<>();
//        for (CategoryBrand brand : list) {
//            Brand brand1 = brandMapper.selectByPrimaryKey(brand.getBrandId());
//            brands.add(brand1);
//        }
//        return brands;
        return categoryBrandMapper.findBrandByCid(id);
    }
}
