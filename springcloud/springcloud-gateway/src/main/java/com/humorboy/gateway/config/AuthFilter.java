package com.humorboy.gateway.config;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.humorboy.gateway.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 鉴权过滤器
 * Ordered 接口是管理该过滤器执行优先级，数字越小越优先
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private AuthLogin login;

    @Autowired
    JwtHelper jwtHelper;

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {


        //1.判断是否是登录请求，是的话调用登录接口
        ServerHttpRequest request = exchange.getRequest();
        RequestPath path = request.getPath();
        String loginPath = path.toString();
        HttpMethod httpMethod = exchange.getRequest().getMethod();
        boolean method = httpMethod.matches("POST");
        ServerHttpResponse response = exchange.getResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        if ("/login".equals(loginPath) && method) {
            //获取请求体参数
            List<String> userName = exchange.getRequest().getQueryParams().get("userName");
            List<String> pwd = exchange.getRequest().getQueryParams().get("pwd");
            try {
                User user = new User();
                user.setUserName(userName.get(0));
                user.setPwd(pwd.get(0));
                Map<String, Map<String, String>> map = (Map<String, Map<String, String>>) login.login(user);
                //判断用户是够登录成功并进行相应操作
               if ("200".equals(String.valueOf(map.get("status")))) {
                    Map<String, String> userMap = map.get("data");
                   String token = "gateway:" + String.valueOf(userMap.get("id"));
                   String sign = jwtHelper.sign(String.valueOf(userMap.get("id")), userMap.get("userName"));
                    redisTemplate.opsForValue().set(token, sign);
                    Map<String, String> result = new HashMap<String, String>();
                   result.put("status", "200");
                   result.put("msg", "用户登录成功");
                   //result.put("token",sign);
                    //response.addCookie(ResponseCookie.from("access-token", token).build());
                   response.addCookie(ResponseCookie.from("access-sign", sign).build());
                   return setReturn(response, result);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Map<String, String> result = new HashMap<String, String>();
                result.put("status", "404");
                result.put("msg", "出错啦，请检查");
                return setReturn(response, result);
            }
        }
        String token = null;
        String sign = null;
        String redisToken = null;
        try {
            sign = exchange.getRequest().getCookies().get("access-sign").get(0).getValue();
            List<String> audience = JWT.decode(sign).getAudience();
            token = "gateway:" + audience.get(0);
            //token = exchange.getRequest().getCookies().get("access-token").get(0).getValue();
            redisToken = redisTemplate.opsForValue().get(token).toString();
            //2.token是否存在/有效，不存在则重定向到登录页面
            if (redisToken == null || redisToken.isEmpty()) {
                // 封装错误信息
                Map<String, Object> responseData = Maps.newHashMap();
                responseData.put("code", 400);
                responseData.put("message", "用户未登录");
                responseData.put("cause", "Token is empty");
                return setReturn(response, responseData);
            } else {
                if (!redisToken.equals(sign)) {
                    Map<String, String> result = new HashMap<String, String>();
                    result.put("fild", "400");
                    result.put("msg", "字符串验证失败");
                    return setReturn(response, result);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 封装错误信息
            Map<String, Object> responseData = Maps.newHashMap();
            responseData.put("code", 402);
            responseData.put("message", "未找到token");
            responseData.put("cause", "token为空，请重新登录");
            return setReturn(response, responseData);
        }

        //3.验证token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
        try {
            jwtVerifier.verify(redisToken);
        } catch (JWTVerificationException e) {
            Map<String, Object> responseData = Maps.newHashMap();
            responseData.put("code", 402);
            responseData.put("message", "token验证失败");
            responseData.put("cause", "token验证失败");
            return setReturn(response, responseData);
        }

        final String transmitToken = sign;
        ServerHttpRequest host = exchange.getRequest().mutate().headers((HttpHeaders httpHeaders) -> {
            httpHeaders.add("access", transmitToken);
        }).build();
        ServerWebExchange build = exchange.mutate().request(host).build();
        return chain.filter(build);
    }

    /**
     * 设置过滤器的执行顺序
     *
     * @return
     */
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    private Mono<Void> setReturn(ServerHttpResponse originalResponse, Object returnString) {
        originalResponse.setStatusCode(HttpStatus.OK);
        DataBuffer buffer = originalResponse.bufferFactory().wrap(JSONObject.toJSONString(returnString).getBytes(StandardCharsets.UTF_8));
        originalResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return originalResponse.writeWith(Flux.just(buffer));
    }
}