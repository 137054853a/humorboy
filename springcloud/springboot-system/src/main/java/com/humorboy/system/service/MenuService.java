package com.humorboy.system.service;

import com.alibaba.fastjson.JSON;
import com.humorboy.system.repository.MenuRepository;
import com.humorboy.system.repository.UserRepository;
import com.humorboy.commons.vo.system.Menu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class MenuService {

    @Autowired
    MenuRepository menuRepository;
    @Autowired
    UserRepository userRepository;
    @Value("${spring.application.name}")
    private String moduleName;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public List<Menu> findAll() {
        List<Menu> menus = menuRepository.findAll();
        return menus;
    }


    /**
     * 查询用户菜单
     *
     * @param
     * @return
     */
    public List<Menu> findUMenu(String id) {
        //根据用户ID获取对应的菜单
        List<Long> menuIds = JSON.parseArray((String) redisTemplate.opsForValue().get(moduleName + ":menuIds:" + id), Long.class);
        Sort.Order order = Sort.Order.asc("orderNum");
        Sort sort = Sort.by(order);
        List<Menu> menus = menuRepository.findByMenuIdIn(menuIds, sort);
        //用户菜单存入redis
        redisTemplate.opsForSet().add("menu", JSON.toJSONString(menus));
        return menus;


    }

//    public List<PermissionDto> loadUserResources(Long userId){
//        User user = userRepository.findByUserId(userId);
//        Set<Role> roles = user.getRoles();
//        List<Permission> permissionList = new ArrayList<>();
//        for(Role role:roles){
//            Set<Permission>  permissions = role.getPermissions();
//            for(Permission permission:permissions){
////                if(menu.getType().equals("1")){
//                permissionList.add(permission);
////                }
//            }
//        }
////        .stream().map(convertToVo).collect(Collectors.toList())
//        List<PermissionDto> collect = permissionList.stream().map(convertToVo).collect(Collectors.toList());
//        return collect;
//
//    }
//
//    Function<Permission, PermissionDto> convertToVo = apply -> {
//        PermissionDto permissionDto = permissionMapstruct.convertDto(apply);
//        return permissionDto;
//    };


}
