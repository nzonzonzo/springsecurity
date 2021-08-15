package com.changgou.search.controller;

import com.changgou.search.service.SearchService;
import entry.Result;
import entry.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/import")
    public Result importFromDbToEs(){
        searchService.importFromDbToEs();
        return new Result(true, StatusCode.OK, "保存成功");
    }

    @PostMapping
    public Map search(@RequestBody Map<String, String> searchMap) {
        return searchService.search(searchMap);
    }
}
