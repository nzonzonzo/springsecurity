package com.nzo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringSecurityController {

    @PostMapping("/success")
    public String success(){
        return "hello success";
    }

    @GetMapping("user/login")
    public String login(String username,String password){
        System.out.println(username+password);
        return "success";
    }
}
