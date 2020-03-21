package com.humorboy.system.service;

import com.alibaba.fastjson.JSON;
import com.humorboy.system.repository.MenuRepository;
import com.humorboy.system.repository.PermissionRepository;
import com.humorboy.system.repository.UserRepository;
import com.humorboy.commons.vo.system.Permission;
import com.humorboy.commons.vo.system.Role;
import com.humorboy.commons.vo.system.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
@ConfigurationProperties(prefix = "name")
public class PermissionService {

    @Autowired
    UserRepository userRepository;

    @Value("${spring.application.name}")
    private String moduleName;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    MenuRepository menuRepository;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 获取用户资源
     *
     * @param userId
     * @return
     */
    public Set<Permission> loadUserResources(Long userId) {
        User user = userRepository.findByUserId(userId);
        Set<Role> roles = user.getRoles();
        //返回所有资源信息 不重复
        Set<Permission> permissions = selectPermis(roles);
        //存入redis
        redisTemplate.opsForValue().set("permission", permissions);

        return permissions;
    }


    /**
     * 根据角色 获取不重复资源
     *
     * @param roles
     * @return
     */
    public Set<Permission> selectPermis(Set<Role> roles) {
        Set<Permission> permissionList = new HashSet<>();
        Set<Permission> permissions = new HashSet<>();
        List<Permission> permisss = new ArrayList<>();
        //获取所有资源信息

        for (Iterator<Role> iterator = roles.iterator(); iterator.hasNext(); ) {
            Role role = iterator.next();
            //如果包含管理员角色 查询所有资源
            if (role.getIsManager() == 1) {
                permisss = permissionRepository.findAll();
                permissions = (Set<Permission>) permisss;
            }
            permissions.add((Permission) role.getPermissions());
        }
        //去除重复资源
        for (Iterator<Permission> iterator = permissions.iterator(); iterator.hasNext(); ) {
            permissionList.add(iterator.next());
        }
        return permissionList;
    }

    /**
     * 登录用户信息 资源存入redis
     */
    public void addRedis(User loginUser) {
        Set<Role> roles = loginUser.getRoles();
        Set<Long> permissionSet = new HashSet<>();
        for (Role role : roles) {
            //如果包含管理员角色 查询所有资源
            if (role.getIsManager() == 1) {
                List<Permission> permisss = permissionRepository.findAll();
                for (Permission permission : permisss) {
                    permissionSet.add(permission.getMenuId());
                }
            }
            Set<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
                permissionSet.add(permission.getMenuId());
            }

        }
        //转为json字符串
        String permissionSetString = JSON.toJSONString(permissionSet);
        //redis存入用户菜单
        redisTemplate.opsForValue().set(moduleName + ":menuIds:" + loginUser.getId(), permissionSetString);
    }

}
