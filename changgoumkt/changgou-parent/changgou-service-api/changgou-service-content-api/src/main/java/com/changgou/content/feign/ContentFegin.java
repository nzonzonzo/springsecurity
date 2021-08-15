package com.changgou.content.feign;


import com.changgou.content.pojo.Content;
import entry.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "content",path = "/content")
public interface ContentFegin {

    @GetMapping("/list/category/{id}")
    public Result<List<Content>> findByCategory(@PathVariable Long id);
}
