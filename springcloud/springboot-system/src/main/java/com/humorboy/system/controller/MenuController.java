package com.humorboy.system.controller;

import com.auth0.jwt.JWT;
import com.humorboy.commons.vo.system.Menu;
import com.humorboy.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuService menuService;


    /**
     * 获取用户菜单
     * @return
     */
    @PostMapping("/listMenu")
    public List<Menu> listMenu(HttpServletRequest request) {
        //获取cookie中的token
        Cookie[] cookies = request.getCookies();
        String sign = null;
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                sign = cookie.getValue();
            }
        }
        //对token解密
        List<String> audience = JWT.decode(sign).getAudience();
        //获取用户对应id
        String id = audience.get(0);
        List<Menu> menus = menuService.findUMenu(id);
        return menus;
    }


}
