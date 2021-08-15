package com.changgou.goods.controller;

import com.changgou.core.AbstractCoreController;
import com.changgou.goods.pojo.Category;
import com.changgou.goods.service.CategoryService;
import entry.Result;
import entry.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/****
 * @Author:admin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController extends AbstractCoreController<Category>{

    private CategoryService  categoryService;

    @Autowired
    public CategoryController(CategoryService  categoryService) {
        super(categoryService, Category.class);
        this.categoryService = categoryService;
        }

    @GetMapping("/list/{pid}")
    public Result<List<Category>> findByParentId(@PathVariable Integer pid) throws Exception {
        List<Category> list = categoryService.findByParentId(pid);
        if (!CollectionUtils.isEmpty(list)) {
            return new Result<List<Category>>(true, StatusCode.OK, "根据父节点查询分类成功",list);
        }
        throw new Exception("没有找到");
    }

}
