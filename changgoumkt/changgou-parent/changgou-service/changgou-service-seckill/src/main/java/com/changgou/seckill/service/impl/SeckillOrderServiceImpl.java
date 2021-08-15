package com.changgou.seckill.service.impl;

import com.changgou.core.service.impl.CoreServiceImpl;
import com.changgou.seckill.Task.MultiThreadCreateOrder;
import com.changgou.seckill.dao.SeckillGoodsMapper;
import com.changgou.seckill.dao.SeckillOrderMapper;
import com.changgou.seckill.pojo.SeckillGoods;
import com.changgou.seckill.pojo.SeckillOrder;
import com.changgou.seckill.service.SeckillOrderService;
import com.changgou.seckill.status.SeckillStatus;
import entry.IdWorker;
import entry.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/****
 * @Author:admin
 * @Description:SeckillOrder业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class SeckillOrderServiceImpl extends CoreServiceImpl<SeckillOrder> implements SeckillOrderService {

    private SeckillOrderMapper seckillOrderMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;

    @Autowired
    private MultiThreadCreateOrder multiThreadCreateOrder;

    @Autowired
    public SeckillOrderServiceImpl(SeckillOrderMapper seckillOrderMapper) {
        super(seckillOrderMapper, SeckillOrder.class);
        this.seckillOrderMapper = seckillOrderMapper;
    }

    /**
     * 往redis中下预订单
     * @param name
     * @param time
     * @param id
     * @return
     */
    @Override
    public Boolean addOrder(String name, String time, Long id) {

        //1.获取秒杀商品信息
        SeckillGoods seckillGoods = (SeckillGoods) redisTemplate.boundHashOps(SystemConstants.SEC_KILL_GOODS_PREFIX + time).get(id);
        //2.如果商品为null或库存<=0则直接抛出异常
        if (seckillGoods == null || seckillGoods.getStockCount() <= 0){
            throw new RuntimeException("已售罄");
        }
        //3.将用户下单信息压入队列(redis list)
        SeckillStatus seckillStatus = new SeckillStatus(name,new Date(),1,id,time);
        redisTemplate.boundListOps(SystemConstants.SEC_KILL_USER_STATUS_KEY).leftPush(seckillStatus);
        multiThreadCreateOrder.Order();
        return true;
    }

}
