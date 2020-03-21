package com.humorboy.gateway.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: CC菜
 * @Date: 2019/9/12 10:29
 * @Remark
 */
@Configuration
public class JwtConfig {

    @Value("${jwt.secret}")
    private String secret;

    @Bean
    public Algorithm algorithm() {
        return Algorithm.HMAC256(this.secret);
    }

    @Bean
    public JWTVerifier jwtVerifier() {
        return JWT.require(algorithm()).build();
    }


}
