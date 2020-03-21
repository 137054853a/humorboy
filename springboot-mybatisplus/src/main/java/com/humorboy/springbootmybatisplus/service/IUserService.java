package com.humorboy.springbootmybatisplus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.humorboy.springbootmybatisplus.constant.ResultInfo;
import com.humorboy.springbootmybatisplus.vo.User;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jobob
 * @since 2020-03-10
 */
@RestController
@RequestMapping(value = "/user")
public interface IUserService extends IService<User> {

    @PostMapping(value = "/save")
    public ResultInfo createUser(@RequestBody User user);

    @PostMapping(value = "/update")
    public ResultInfo updateUser(User user);

    @PostMapping(value = "/delet/{id}")
    public ResultInfo deletUser(@PathVariable Integer id);

    @PostMapping(value = "/delets")
    public ResultInfo batchUser(String ids);

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    IPage<User> findAll(int page, int limit);
}
