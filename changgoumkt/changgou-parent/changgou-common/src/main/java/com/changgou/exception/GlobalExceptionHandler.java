package com.changgou.exception;

import entry.Result;
import entry.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServlet;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //写一个方法  用于 当发生了异常由该方法来捕获并处理 返回符合需求的响应值
    // @ExceptionHandler(value= Exception.class)  修饰方法 标识该方法需要处理异常的信息
    //  Exception.class 只要发生了 exeption的子类的异常，都会被捕获
    //只能针对 有@requestMapping--》getmpping postmping注解修饰的方法
    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception e){

        e.printStackTrace(); //打印堆信息

        return new Result(false, StatusCode.ERROR, e.getMessage());

    }
    // 商品找不到 有一个异常类  ItemNotFOUNDException
   /* @ExceptionHandler(value= ItemNotFOUNDException.class)
    @ResponseBody
    public Result handlerException(Exception e){
        //捕获异常
        e.printStackTrace();
        //处理结果
        return new Result(false, StatusCode.ERROR,e.getMessage());
    }*/
    // 订单不到 有一个异常类  OrderNotFOUNDException
}
