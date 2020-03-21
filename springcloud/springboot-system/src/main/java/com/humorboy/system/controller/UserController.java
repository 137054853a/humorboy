package com.humorboy.system.controller;

import com.humorboy.system.dto.UserDto;
import com.humorboy.system.service.RoleService;
import com.humorboy.system.service.UserService;
import com.humorboy.commons.constant.ResultInfo;
import com.humorboy.commons.vo.system.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    RoleService roleService;

    /**
     * 用户分页查询
     *
     * @param userDto
     * @return
     */
    @PostMapping("/pageList")
    public Map<String, Object> pageList(@RequestBody UserDto userDto) {
        if (userDto.getPage() == 0) {
            userDto.setPage(1);
        }
        if (userDto.getLimit() == 0) {
            userDto.setLimit(10);
        }
        Map<String, Object> userPage = userService.listUser(userDto);
        return userPage;

    }

    /**
     * 新增单个用户
     *
     * @param userDto
     * @return
     */
    @PostMapping("/addUser")
    public ResultInfo addUser(@RequestBody UserDto userDto) {
        if (StringUtils.isBlank(userDto.getUserName())) {
            log.error("用户名不能为空！");
            return ResultInfo.buildFailed("用户名不能为空！");
        }
        if (StringUtils.isBlank(userDto.getPwd())) {
            log.error("密码不能为空！");
            return ResultInfo.buildFailed("密码不能为空！");
        }
        //查询用户名是否已存在
        User user = userService.findByUserName(userDto.getUserName());
        if (user != null) {
            return ResultInfo.buildFailed("用户名已存在！");
        }

            //开始新增
        boolean info = userService.save(userDto);
        if (!info) {
            return ResultInfo.buildFailed("新增失败！");
        }
        return ResultInfo.buildSuccess("新增成功");
    }

    /**
     * 修改
     *
     * @return
     */
    @RequestMapping("/updateUser")
    public ResultInfo updateUser(@RequestBody UserDto userDto) {
        if (userDto.getUserId() == null) {
            return ResultInfo.buildFailed("用户id不能为空！");
        }
        Boolean info = userService.update(userDto);
        if (info != true) {
            return ResultInfo.buildFailed();
        }
        return ResultInfo.buildSuccess();
    }


    /**
     * 单个删除
     *
     * @return
     */
    @RequestMapping("/deleteOne")
    public ResultInfo deleteOne(@RequestBody UserDto userDto) {
        if (userDto.getId() == null) {
            return ResultInfo.buildFailed("id为不能空");
        }
        boolean info = userService.deleteOne(userDto.getId());
        if (info != true) {
            return ResultInfo.buildFailed("删除失败！");
        }
        return ResultInfo.buildSuccess("删除成功！");
    }


    /**
     * 项目管理查询所有用户
     *
     * @return
     */
    @RequestMapping("/selectAll")
    public ResultInfo projectUser() {
        List<UserDto> users = userService.findAll();
        return ResultInfo.buildSuccess(users);
    }


}
