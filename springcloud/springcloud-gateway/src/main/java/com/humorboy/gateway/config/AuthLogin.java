package com.humorboy.gateway.config;


import com.humorboy.gateway.vo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author: CC菜
 * @Date: 2019/9/6 17:06
 * @Remark
 */
@FeignClient(value = "springboot-system")
public interface AuthLogin {

    @PostMapping(value = "/login")
    Object login(User user);


}
