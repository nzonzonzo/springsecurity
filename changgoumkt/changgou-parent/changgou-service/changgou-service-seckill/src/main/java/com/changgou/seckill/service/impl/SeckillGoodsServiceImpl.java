package com.changgou.seckill.service.impl;

import com.changgou.core.service.impl.CoreServiceImpl;
import com.changgou.seckill.dao.SeckillGoodsMapper;
import com.changgou.seckill.pojo.SeckillGoods;
import com.changgou.seckill.service.SeckillGoodsService;
import entry.DateUtil;
import entry.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/****
 * @Author:admin
 * @Description:SeckillGoods业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class SeckillGoodsServiceImpl extends CoreServiceImpl<SeckillGoods> implements SeckillGoodsService {

    private SeckillGoodsMapper seckillGoodsMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    public SeckillGoodsServiceImpl(SeckillGoodsMapper seckillGoodsMapper) {
        super(seckillGoodsMapper, SeckillGoods.class);
        this.seckillGoodsMapper = seckillGoodsMapper;
    }

    @Override
    public SeckillGoods findOne(Long id,String time) {
        SeckillGoods seckillGoods = (SeckillGoods) redisTemplate.boundHashOps(SystemConstants.SEC_KILL_GOODS_PREFIX + time).get(id);
        return seckillGoods;
    }

    @Override
    public List<SeckillGoods> findList(String time) {
        List<SeckillGoods> list = redisTemplate.boundHashOps(SystemConstants.SEC_KILL_GOODS_PREFIX + time).values();
        return list;
    }
}
