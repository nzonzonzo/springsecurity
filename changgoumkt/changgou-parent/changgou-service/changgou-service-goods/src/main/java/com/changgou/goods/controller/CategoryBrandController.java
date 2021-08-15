package com.changgou.goods.controller;

import com.changgou.core.AbstractCoreController;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.pojo.CategoryBrand;
import com.changgou.goods.service.CategoryBrandService;
import entry.Result;
import entry.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:admin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/

@RestController
@RequestMapping("/categoryBrand")
@CrossOrigin
public class CategoryBrandController extends AbstractCoreController<CategoryBrand>{

    private CategoryBrandService  categoryBrandService;

    @Autowired
    public CategoryBrandController(CategoryBrandService  categoryBrandService) {
        super(categoryBrandService, CategoryBrand.class);
        this.categoryBrandService = categoryBrandService;
        }

    @GetMapping("/category/{id}")
    public Result<List<Brand>> findBrandByCid(@PathVariable Integer id) {
        List<Brand> list = categoryBrandService.findBrandByCid(id);
        return new Result<>(true, StatusCode.OK, "查询品牌数据成功", list);
    }
}
