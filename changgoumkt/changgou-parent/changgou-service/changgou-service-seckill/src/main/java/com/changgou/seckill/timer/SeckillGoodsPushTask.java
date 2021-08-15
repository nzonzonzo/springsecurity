package com.changgou.seckill.timer;


import com.changgou.seckill.dao.SeckillGoodsMapper;
import com.changgou.seckill.pojo.SeckillGoods;
import entry.DateUtil;
import entry.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
public class SeckillGoodsPushTask {

    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 把数据库秒杀商品的数据压入到redis中
     */
    @Scheduled(cron = "0/30 * * * * ?")
    public void loadGoodsPushRedis(){
        List<Date> dateMenus = DateUtil.getDateMenus();
        for (Date dateMenu : dateMenus) {
            String extTime = DateUtil.data2str(dateMenu, DateUtil.PATTERN_YYYYMMDDHH);
            Example example = new Example(SeckillGoods.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andGreaterThan("stockCount", 0);
            criteria.andEqualTo("status", 1);
            //seckillGoods表中有start_time和end_time两个字段用来标记当前商品所属时间段
            criteria.andGreaterThanOrEqualTo("startTime",dateMenu);
            criteria.andLessThan("endTime", DateUtil.addDateHour(dateMenu, 2));
            //not in redis中
            Set keys = redisTemplate.boundHashOps(extTime).keys();
            if (keys != null && keys.size() > 0) {
                criteria.andNotIn("id", keys);
            }
            List<SeckillGoods> seckillGoods = seckillGoodsMapper.selectByExample(example);
            for (SeckillGoods seckillGood : seckillGoods) {
                redisTemplate.boundHashOps(SystemConstants.SEC_KILL_GOODS_PREFIX+extTime)
                        .put(seckillGood.getId(),seckillGood);
//                redisTemplate.boundHashOps(SystemConstants.SEC_KILL_GOODS_PREFIX + extTime).
//                        expireAt(DateUtil.addDateHour(dateMenu, 2));
            }
        }
    }
}
