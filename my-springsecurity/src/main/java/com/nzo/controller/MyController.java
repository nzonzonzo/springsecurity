package com.nzo.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class MyController {

    @RequestMapping("login")
    @CrossOrigin
    public String login(String username,String password){
        System.out.println(username+password);
        System.out.println("方法执行");
        System.out.println("方法执行啦");
        return "denglu chenggong";
    }
}
