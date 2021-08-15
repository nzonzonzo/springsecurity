package com.changgou.goods.controller;

import com.changgou.core.AbstractCoreController;
import com.changgou.goods.pojo.Category;
import com.changgou.goods.pojo.Spec;
import com.changgou.goods.service.SpecService;
import entry.Result;
import entry.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/****
 * @Author:admin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/

@RestController
@RequestMapping("/spec")
@CrossOrigin
public class SpecController extends AbstractCoreController<Spec>{

    private SpecService  specService;

    @Autowired
    public SpecController(SpecService  specService) {
        super(specService, Spec.class);
        this.specService = specService;
        }

    @GetMapping("/category/{id}")
    public Result<List<Spec>> findSpecByCategory(@PathVariable Integer id) {
        List<Spec> list = specService.findSpecByCategory(id);
        return new Result<>(true, StatusCode.OK, "查询规格参数成功", list);
    }
}
