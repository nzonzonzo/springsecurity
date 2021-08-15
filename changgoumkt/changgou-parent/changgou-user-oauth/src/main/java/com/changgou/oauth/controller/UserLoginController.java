package com.changgou.oauth.controller;


import com.changgou.oauth.service.UserLoginService;
import com.changgou.oauth.util.CookieUtil;
import entry.Result;
import entry.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserLoginController {

    @Value("${auth.clientId}")
    private String clientId;

    @Value("${auth.clientSecret}")
    private String clientSecret;

    @Value("${auth.cookieDomain}")
    private String cookieDomain;

    @Value("${auth.cookieMaxAge}")
    private Integer cookieMaxAge;

    @Autowired
    private UserLoginService userLoginService;

    @GetMapping("/login")
    public Result login(String username,String password){
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return new Result(false, StatusCode.LOGINERROR, "用户名或密码不能为空");
        }
        Map<String, String> responseEntityMap = userLoginService.login(clientId, clientSecret, username, password);

        String access_token = responseEntityMap.get("access_token");

        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        CookieUtil.addCookie(response,cookieDomain,"/","Authorization",access_token,cookieMaxAge,true);

        return new Result(true, StatusCode.OK,"登录成功！");

    }
}
