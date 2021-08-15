package com.changgou.goods.feign;


import com.changgou.goods.pojo.Sku;
import entry.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "goods",path = "/sku")
public interface SearchSkuFeign {

    @GetMapping("/status/{status}")
    public Result<List<Sku>> searchSku(@PathVariable("status") String status);
}
