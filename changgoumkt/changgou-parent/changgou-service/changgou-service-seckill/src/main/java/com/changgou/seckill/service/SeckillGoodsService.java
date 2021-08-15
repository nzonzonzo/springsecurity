package com.changgou.seckill.service;

import com.changgou.core.service.CoreService;
import com.changgou.seckill.pojo.SeckillGoods;

import java.util.Date;
import java.util.List;

/****
 * @Author:admin
 * @Description:SeckillGoods业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface SeckillGoodsService extends CoreService<SeckillGoods> {


    SeckillGoods findOne(Long id,String time);

    List<SeckillGoods> findList(String time);
}
