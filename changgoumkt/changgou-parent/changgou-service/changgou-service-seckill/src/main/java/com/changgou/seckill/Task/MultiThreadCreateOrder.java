package com.changgou.seckill.Task;

import com.changgou.seckill.dao.SeckillGoodsMapper;
import com.changgou.seckill.pojo.SeckillGoods;
import com.changgou.seckill.pojo.SeckillOrder;
import com.changgou.seckill.status.SeckillStatus;
import entry.IdWorker;
import entry.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
//@Scope("prototype")
public class MultiThreadCreateOrder {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;


    //目前看这里传参数过来也行，但是因为我们想让数据在redis队列中排队，所以采用了传到redis中的方式
    @Async
    public void Order(){
        SeckillStatus seckillStatus = (SeckillStatus) redisTemplate.boundListOps(SystemConstants.SEC_KILL_USER_STATUS_KEY).rightPop();

        String time = seckillStatus.getTime();

        Long id = seckillStatus.getOrderId();

        String name = seckillStatus.getUsername();

        SeckillGoods seckillGoods = (SeckillGoods) redisTemplate.boundHashOps(SystemConstants.SEC_KILL_GOODS_PREFIX + time).get(id);

        //3.往redis中下预订单
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setId(idWorker.nextId());
        seckillOrder.setSeckillId(seckillGoods.getId());
        seckillOrder.setMoney(seckillGoods.getCostPrice());
        seckillOrder.setCreateTime(new Date());
        seckillOrder.setStatus("0");
        redisTemplate.boundHashOps(SystemConstants.SEC_KILL_ORDER_KEY).put(name,seckillOrder);

        //4.减库存
        seckillGoods.setStockCount(seckillGoods.getStockCount() - 1);
        System.out.println(seckillGoods.getStockCount());
        //5.如果库存没了则写入数据库并且删除redis中的商品数据
        //高并发环境下，因为同时进来的人都下好了订单，库存为0后要往数据库写入，频繁的写入对数据库造成负担，可能会宕机
        if (seckillGoods.getStockCount() <= 0) {
            seckillGoodsMapper.updateByPrimaryKeySelective(seckillGoods);
            redisTemplate.boundHashOps(SystemConstants.SEC_KILL_GOODS_PREFIX + time).delete(id);
        }
        //6.如果还有库存则更新redis中的goods数据
        redisTemplate.boundHashOps(SystemConstants.SEC_KILL_GOODS_PREFIX + time).put(id, seckillGoods);
    }
}
