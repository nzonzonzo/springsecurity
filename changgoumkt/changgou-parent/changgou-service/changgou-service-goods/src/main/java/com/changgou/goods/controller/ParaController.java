package com.changgou.goods.controller;

import com.changgou.core.AbstractCoreController;
import com.changgou.goods.pojo.Para;
import com.changgou.goods.service.ParaService;
import entry.Result;
import entry.StatusCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:admin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/

@RestController
@RequestMapping("/para")
@CrossOrigin
public class ParaController extends AbstractCoreController<Para>{

    private ParaService  paraService;

    @Autowired
    public ParaController(ParaService  paraService) {
        super(paraService, Para.class);
        this.paraService = paraService;
        }

    /**
     * 根据cid查询参数列表
      * @return
     */
    @GetMapping("category/{id}")
    public Result<List<Para>> findParaByCid(@PathVariable Integer id){
        List<Para> list = paraService.findParaByCid(id);
        return new Result<>(true, StatusCode.OK, "查询成功", list);
    }
}
