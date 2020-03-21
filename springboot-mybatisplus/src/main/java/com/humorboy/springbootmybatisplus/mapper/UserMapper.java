package com.humorboy.springbootmybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.humorboy.springbootmybatisplus.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Date 9:40
 * @Author PC028
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user")
    List<User> findAll();

    @Select("select * from user where user_name = #{userName} and pwd = #{pwd}")
    User findUser(@Param("userName") String userName, @Param("pwd") String pwd);
}
