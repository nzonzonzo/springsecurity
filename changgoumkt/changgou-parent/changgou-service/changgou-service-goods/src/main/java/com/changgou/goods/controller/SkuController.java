package com.changgou.goods.controller;

import com.changgou.core.AbstractCoreController;
import com.changgou.goods.pojo.Sku;
import com.changgou.goods.service.SkuService;
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
@RequestMapping("/sku")
@CrossOrigin
public class SkuController extends AbstractCoreController<Sku>{

    private SkuService skuService;

    @Autowired
    public SkuController(SkuService  skuService) {
        super(skuService, Sku.class);
        this.skuService = skuService;
        }

    @GetMapping("/status/{status}")
    public Result<List<Sku>> searchSku(@PathVariable("status") String status){
        List<Sku> list = skuService.selectSku(status);
        return new Result<>(true, StatusCode.OK, "查询sku成功", list);
    }
}
