package com.humorboy.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EntityScan("com.zshq.commons.vo.system")
public class SpringbootSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootSystemApplication.class, args);
    }

}
