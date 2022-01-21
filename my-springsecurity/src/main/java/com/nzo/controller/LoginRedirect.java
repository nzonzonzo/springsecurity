package com.nzo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class LoginRedirect {

    @GetMapping("/denglu")
    public String denglu(){
        System.out.println("Denglu方法执行了");
        return "login";
        System.out.println("已经return");
    }
}
