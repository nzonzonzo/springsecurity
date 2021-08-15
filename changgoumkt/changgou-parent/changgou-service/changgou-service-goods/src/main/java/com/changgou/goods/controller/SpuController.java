package com.changgou.goods.controller;

import com.changgou.core.AbstractCoreController;
import com.changgou.goods.pojo.Goods;
import com.changgou.goods.pojo.Spu;
import com.changgou.goods.service.SpuService;
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
@RequestMapping("/spu")
@CrossOrigin
public class SpuController extends AbstractCoreController<Spu>{

    private SpuService  spuService;

    @Autowired
    public SpuController(SpuService  spuService) {
        super(spuService, Spu.class);
        this.spuService = spuService;
        }

    /**
     * 保存商品
      * @param goods
     * @return
     */
    @PostMapping("save")
    public Result saveGoods(@RequestBody Goods goods){
        spuService.saveGoods(goods);
        return new Result(true, StatusCode.OK,"添加成功",null);
    }

    /**
     * 根据id查询商品
     * @param id
     * @return
     */
    @GetMapping("goods/{id}")
    public Result<Goods> findGoodsById(@PathVariable Long id) {
        Goods goods = spuService.findGoodsById(id);
        return new Result<>(true,StatusCode.OK,"查询商品成功",goods);
    }

    /**
     * 更新商品
     * @param goods
     * @return
     */
    @PutMapping("update")
    public Result updateGoods(@RequestBody Goods goods) {
        spuService.updateGoods(goods);
        return new Result(true, StatusCode.OK, "更新成功", null);
    }
}
