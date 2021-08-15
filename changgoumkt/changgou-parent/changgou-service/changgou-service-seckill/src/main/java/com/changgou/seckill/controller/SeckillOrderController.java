package com.changgou.seckill.controller;

import com.changgou.core.AbstractCoreController;
import com.changgou.seckill.pojo.SeckillOrder;
import com.changgou.seckill.service.SeckillOrderService;
import entry.Result;
import entry.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/****
 * @Author:admin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/

@RestController
@RequestMapping("/seckillOrder")
@CrossOrigin
public class SeckillOrderController extends AbstractCoreController<SeckillOrder>{

    private SeckillOrderService  seckillOrderService;

    @Autowired
    public SeckillOrderController(SeckillOrderService  seckillOrderService) {
        super(seckillOrderService, SeckillOrder.class);
        this.seckillOrderService = seckillOrderService;
        }

    /**
     * 下预订单到redis中
     * @param time
     * @param id
     * @return
     */
    @GetMapping("/add")
    public Result addOrder(String time,Long id){
        //1.获取用户名(这里做测试，真正的用户名在登录的token里获取)
//        String name = "zhangsan";
        String name = UUID.randomUUID().toString();
        Boolean flag = seckillOrderService.addOrder(name, time, id);
        if (flag){
            return new Result(true, StatusCode.OK,"创建预订单成功");
        }
        return new Result(false,StatusCode.ERROR,"创建预订单失败");
    }
}
