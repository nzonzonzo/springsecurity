package com.changgou.oauth.service;

import java.util.Map;

public interface UserLoginService {
    Map<String,String> login(String clientId, String clientSecret, String username, String password);
}
