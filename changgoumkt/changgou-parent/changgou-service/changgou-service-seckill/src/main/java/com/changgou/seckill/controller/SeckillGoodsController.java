package com.changgou.seckill.controller;

import com.changgou.core.AbstractCoreController;
import com.changgou.seckill.pojo.SeckillGoods;
import com.changgou.seckill.service.SeckillGoodsService;
import com.changgou.seckill.service.impl.SeckillGoodsServiceImpl;
import entry.DateUtil;
import entry.Result;
import entry.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/****
 * @Author:admin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/

@RestController
@RequestMapping("/seckillGoods")
@CrossOrigin
public class SeckillGoodsController extends AbstractCoreController<SeckillGoods> {

    private SeckillGoodsService seckillGoodsService;

    @Autowired
    public SeckillGoodsController(SeckillGoodsService seckillGoodsService) {
        super(seckillGoodsService, SeckillGoods.class);
        this.seckillGoodsService = seckillGoodsService;
    }

    /**
     * 获取前端秒杀时间集合
     *
     * @return
     */
    @GetMapping("/menu")
    public List<Date> menus() {
        List<Date> dateMenus = DateUtil.getDateMenus();
//        for (int i = 0; i < dateMenus.size(); i++) {
//            System.out.println(dateMenus.get(i));
//        }
        return DateUtil.getDateMenus();
    }

    /**
     * 获取某个秒杀时间段的商品数据
     *
     * @param time
     * @return
     */
    @GetMapping("list") // TODO: 2021/7/1 如何实现分页
    public Result<List<SeckillGoods>> getGoodsList(String time) {
        List<SeckillGoods> list = seckillGoodsService.findList(time);
        if (list != null && list.size() > 0) {
            return new Result<>(true, StatusCode.OK, "查询成功", list);
        } else
            return new Result<>(false, StatusCode.ERROR, "没有查询到相关商品");
    }

    /**
     * 获取某个具体商品的数据
     *
     * @param id
     * @param time
     * @return
     */
    @GetMapping("one")
    public Result<SeckillGoods> getOneGoods(Long id, String time) {
        SeckillGoods seckillGoods = seckillGoodsService.findOne(id, time);
        if (seckillGoods != null) {
            return new Result<>(true, StatusCode.OK, "查询成功", seckillGoods);
        } else
            return new Result<>(false, StatusCode.ERROR, "没有查询到相关商品");
    }
}


