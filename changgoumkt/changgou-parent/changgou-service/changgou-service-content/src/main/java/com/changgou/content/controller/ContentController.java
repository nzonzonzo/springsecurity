package com.changgou.content.controller;

import com.changgou.content.pojo.Content;
import com.changgou.content.service.ContentService;
import com.changgou.core.AbstractCoreController;
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
@RequestMapping("/content")
@CrossOrigin
public class ContentController extends AbstractCoreController<Content>{

    private ContentService  contentService;

    @Autowired
    public ContentController(ContentService  contentService) {
        super(contentService, Content.class);
        this.contentService = contentService;
        }

    @GetMapping("/list/category/{id}")
    public Result<List<Content>> findByCategory(@PathVariable Long id) {
        Content content = new Content();
        content.setCategoryId(id);
        List<Content> list = contentService.select(content);
        return new Result<List<Content>>(true, StatusCode.OK,"查询成功",list);
    }
}
