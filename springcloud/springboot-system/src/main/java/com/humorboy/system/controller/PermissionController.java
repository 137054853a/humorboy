package com.humorboy.system.controller;

import com.humorboy.commons.vo.system.Permission;
import com.humorboy.system.service.PermissionService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Set;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 查询用户资源
     *
     * @return
     */
    @RequestMapping("/selectPermission")
    public Set<Permission> selectPermission() {
        Long userId = (Long) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
        Set<Permission> dtos = permissionService.loadUserResources(userId);

        return dtos;
    }

}
