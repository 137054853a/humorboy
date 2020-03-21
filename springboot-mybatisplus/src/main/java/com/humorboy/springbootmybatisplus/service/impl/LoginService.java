package com.humorboy.springbootmybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.humorboy.springbootmybatisplus.constant.ResultInfo;
import com.humorboy.springbootmybatisplus.mapper.UserMapper;
import com.humorboy.springbootmybatisplus.service.ILoginService;
import com.humorboy.springbootmybatisplus.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-03-10
 */
@Service
public class LoginService extends ServiceImpl<UserMapper, User> implements ILoginService {
    @Autowired
    UserMapper userMapper;

    @Override
    public ResultInfo login(User user) {
        User user1 = userMapper.findUser(user.getUserName(), user.getPwd());
        if (user1 == null) {
            return ResultInfo.buildFailed();
        }
        return ResultInfo.buildSuccess(user);
    }
}
