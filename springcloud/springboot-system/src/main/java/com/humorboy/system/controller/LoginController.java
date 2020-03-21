package com.humorboy.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.client.naming.utils.StringUtils;
import com.humorboy.system.dto.UserDto;
import com.humorboy.system.service.PermissionService;
import com.humorboy.system.service.UserService;
import com.humorboy.system.util.UserMapstruct;
import com.humorboy.commons.constant.ResultInfo;
import com.humorboy.commons.vo.system.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class LoginController {

    @Resource
    UserService userService;

    @Value("${spring.application.name}")
    private String moduleName;

    @Autowired
    PermissionService permissionService;

    @Resource
    UserMapstruct userMapstruct;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * auth 认证调用查询用户是否存在  返回
     * @param
     * @param
     * @return
     */
    @PostMapping(value = "/login")
    public ResultInfo login(@RequestBody User user) {
        log.info("post请求登录");
        String userName = user.getUserName();
        String password = user.getPwd();
        User loginUser = userService.findByUserNamePwd(userName, password);
        if (loginUser == null) {
            log.error("用户不存在！");
            return ResultInfo.buildFailed("用户不存在");
        }
        //转userDto对象  解决关联查询导致返回问题
        UserDto userDto = userMapstruct.convertDto(loginUser);
        //将对象转为json字符串 存入redis
        String userDto1 = JSON.toJSONString(userDto);
        redisTemplate.opsForValue().set(moduleName + ":user:" + loginUser.getId(), userDto1);
//        User login = (User)JSON.parseObject(redisTemplate.opsForValue().get("user").toString(),new TypeReference<User>(){});
        //登录时获取用户所有资源
        permissionService.addRedis(loginUser);
        return ResultInfo.buildSuccess(userDto);
    }

    /**
     * 登录认证之后 请求此方式加载资源  shiro
     *
     * @param user
     * @return
     */
    @PostMapping("/loginIndex")
    public ResultInfo index(@RequestBody User user) {
        log.info("post请求登录");
        String loginname = user.getUserName();
        String password = user.getPwd();
        if (StringUtils.isNotEmpty(loginname)) {
            log.error("用户名不能为空");
            ResultInfo.buildFailed("用户名不能为空！");
        }
        if (StringUtils.isNotEmpty(password)) {
            log.error("密码不能为空");
            ResultInfo.buildFailed("密码不能为空！");
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginname, password);
        try {
            subject.login(token);
            return ResultInfo.buildSuccess("登录成功");
        } catch (UnknownAccountException e) {
            log.error("账号不存在！", e.getMessage());
            return ResultInfo.buildFailed("账号不存在！", e.getMessage());
        } catch (IncorrectCredentialsException e) {
            log.error("密码错误！", e.getMessage());
            return ResultInfo.buildFailed("密码错误！", e.getMessage());
        } catch (Throwable e) {
            log.error(e.getMessage());
            return ResultInfo.buildFailed(e.getMessage());
        }

    }


    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    @ResponseBody
    public ResultInfo logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResultInfo.buildSuccess();
    }

    @GetMapping("/setKey")
    public ResultInfo setKey() {
        redisTemplate.opsForValue().set("test", "test");
        return ResultInfo.buildSuccess();
    }

}
