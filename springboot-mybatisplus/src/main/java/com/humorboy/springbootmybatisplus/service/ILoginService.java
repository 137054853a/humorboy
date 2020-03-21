package com.humorboy.springbootmybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.humorboy.springbootmybatisplus.constant.ResultInfo;
import com.humorboy.springbootmybatisplus.vo.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date 2020/3/11 13:54
 * @Author PC028
 */
@RestController
public interface ILoginService extends IService<User> {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultInfo login(@RequestBody User user);
}
