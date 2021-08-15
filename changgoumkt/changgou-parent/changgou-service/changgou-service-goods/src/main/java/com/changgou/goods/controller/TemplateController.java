package com.changgou.goods.controller;

import com.changgou.core.AbstractCoreController;
import com.changgou.goods.pojo.Template;
import com.changgou.goods.service.TemplateService;
import entry.Result;
import entry.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/****
 * @Author:admin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/

@RestController
@RequestMapping("/template")
@CrossOrigin
public class TemplateController extends AbstractCoreController<Template>{

    private TemplateService  templateService;

    @Autowired
    public TemplateController(TemplateService  templateService) {
        super(templateService, Template.class);
        this.templateService = templateService;
        }


    @GetMapping("/category/{id}")
    public Result<Template> findByCategoryId(@PathVariable("id") Integer id) {
        Template template = templateService.findByCategoryId(id);
        return new Result<>(true, StatusCode.OK, "查询模板成功", template);
    }
}
