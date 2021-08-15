package com.changgou.goods.service;

import com.changgou.core.service.CoreService;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.pojo.CategoryBrand;

import java.util.List;

/****
 * @Author:admin
 * @Description:CategoryBrand业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface CategoryBrandService extends CoreService<CategoryBrand> {

    List<Brand> findBrandByCid(Integer id);
}
