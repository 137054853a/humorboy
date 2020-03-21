package com.humorboy.springbootmybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.humorboy.springbootmybatisplus.constant.ResultInfo;
import com.humorboy.springbootmybatisplus.mapper.UserMapper;
import com.humorboy.springbootmybatisplus.service.IUserService;
import com.humorboy.springbootmybatisplus.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-03-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Autowired
    UserMapper userMapper;

    @Override
    public IPage<User> findAll(int page, int limit) {
        Wrapper<User> queryWrapper = new QueryWrapper<>();
        Page<User> userPage1 = new Page<>(page, limit);
        Page<User> userPage = userMapper.selectPage(userPage1, queryWrapper);
        return userPage;
    }

    @Override
    public ResultInfo createUser(User user) {
        int insert = userMapper.insert(user);
        if (insert == 1) {
            return ResultInfo.buildSuccess();
        }
        return ResultInfo.buildFailed();
    }

    @Override
    public ResultInfo updateUser(User user) {
        userMapper.updateById(user);
        return ResultInfo.buildSuccess();
    }

    @Override
    public ResultInfo deletUser(Integer id) {
        userMapper.deleteById(id);
        return ResultInfo.buildSuccess();
    }

    @Override
    public ResultInfo batchUser(String ids) {
        String[] split = ids.split(",");
        List<String> list = Arrays.asList(split);
        List<Integer> idList = list.stream().map(Integer::valueOf).collect(Collectors.toList());
        userMapper.deleteBatchIds(idList);
        return ResultInfo.buildSuccess();
    }
}
