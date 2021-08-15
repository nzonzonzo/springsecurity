package com.changgou.seckill.test;

import entry.SystemConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChaiMaiTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void Test(){
        Set keys = redisTemplate.boundHashOps(SystemConstants.SEC_KILL_ORDER_KEY).keys();
        Map map = redisTemplate.boundHashOps(SystemConstants.SEC_KILL_ORDER_KEY).entries();
        System.out.println(keys);
        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            System.out.println(next);
        }

    }
}
