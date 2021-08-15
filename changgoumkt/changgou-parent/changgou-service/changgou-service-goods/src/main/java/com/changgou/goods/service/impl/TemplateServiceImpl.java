package com.changgou.goods.service.impl;

import com.changgou.core.service.impl.CoreServiceImpl;
import com.changgou.goods.dao.CategoryMapper;
import com.changgou.goods.dao.TemplateMapper;
import com.changgou.goods.pojo.Category;
import com.changgou.goods.pojo.Template;
import com.changgou.goods.service.TemplateService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/****
 * @Author:admin
 * @Description:Template业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class TemplateServiceImpl extends CoreServiceImpl<Template> implements TemplateService {

    private TemplateMapper templateMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    public TemplateServiceImpl(TemplateMapper templateMapper) {
        super(templateMapper, Template.class);
        this.templateMapper = templateMapper;
    }

    @Override
    public Template findByCategoryId(Integer id) {
        Category category = categoryMapper.selectByPrimaryKey(id);

        Template template = templateMapper.selectByPrimaryKey(category.getTemplateId());
        return template;
    }
}
