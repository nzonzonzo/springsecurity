package com.changgou.gateway.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class AuthorizeFilter implements GlobalFilter, Ordered {

    @Value("${authorize}")
    private String Authorization;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String path = request.getURI().getPath();
        if ("/api/user/login".startsWith(path)){
            chain.filter(exchange);
        }
        String token = request.getQueryParams().getFirst(Authorization);
        if (StringUtils.isEmpty(token)) {
            token = request.getHeaders().getFirst(Authorization);
        }
        if (StringUtils.isEmpty(token)) {
            HttpCookie cookie = request.getCookies().getFirst(Authorization);
            if (cookie != null) {
                token = cookie.getValue();
            }
        }
        if (StringUtils.isEmpty(token)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.setComplete();
        }

        request.mutate().header(Authorization, "Bearer " + token);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
