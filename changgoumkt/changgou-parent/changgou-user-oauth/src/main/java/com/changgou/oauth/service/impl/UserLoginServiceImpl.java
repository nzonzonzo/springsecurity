package com.changgou.oauth.service.impl;

import com.changgou.oauth.service.UserLoginService;
import io.netty.handler.codec.base64.Base64Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Map;

public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Map<String, String> login(String clientId, String clientSecret, String username, String password) {
        String url = "http://localhost:9001/oauth/token";
        String grant_type = "password";

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", grant_type);
        body.add("username", username);
        body.add("password", password);

        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        header.add("Authorization","basic "+ Base64.getEncoder().encodeToString((clientId+":"+clientSecret).getBytes()));

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body,header);
        ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);
        Map responseEntityBody = responseEntity.getBody();
        return responseEntityBody;
    }
}
