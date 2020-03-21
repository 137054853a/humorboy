package com.humorboy.system.controller.manage;

import com.humorboy.system.dto.DepartmentDto;
import com.humorboy.system.dto.UserDto;
import com.humorboy.system.util.ReturnResult;
import com.humorboy.system.util.UserMapstruct;
import com.humorboy.commons.constant.ResultInfo;
import com.humorboy.commons.vo.system.Department;
import com.humorboy.commons.vo.system.User;
import com.humorboy.system.service.UserService;
import com.humorboy.system.service.manage.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: CC菜
 * @Date: 2019/9/17 14:29
 * @Remark
 */

@RestController
@Api(value = "DeptController",description = "部门管理控制器")
@RequestMapping("/deptManage")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private UserService userService;

    @Resource
    private UserMapstruct userMapstruct;

    @PostMapping("/treeListDept")
    @ApiOperation("树部门列表查询")
    public ReturnResult treeListDepartment(){

        //return service.listDepartment();
        DepartmentDto department = deptService.treeListDepartment();
        return  ReturnResult.success(department);
    }

    @PostMapping("/listDept")
    @ApiOperation("部门列表查询")
    public List<DepartmentDto> listDepartment(){

        //return service.listDepartment();
        return  deptService.listDepartment();
    }



    @PostMapping("/addDept")
    @ApiOperation("新增部门")
    public ReturnResult addDept( Department dept){
        deptService.addDept(dept);
        return ReturnResult.success("新增成功");
    }

    @PostMapping("/deleteDept/{id}")
    @ApiOperation("删除部门")
    public ReturnResult deleteDept(@PathVariable Long id){
        deptService.deleteDept(id);
        return ReturnResult.success("删除部门成功");

    }

    @PostMapping("/getDept/{id}")
    @ApiOperation("根据部门id获取部门详细")
    public ReturnResult getDept(@PathVariable Long id){
        return ReturnResult.success("查询成功",deptService.getDept(id));

    }

    @PostMapping("/updateDept")
    @ApiOperation("更新部门信息")
    public ResultInfo updateDept( Department dept){
        deptService.updateDept(dept);
        return ResultInfo.buildSuccess("更新部门信息成功");

    }

    @GetMapping ("/getUser")
    @ApiOperation("查询部门下的员工")
    public ResultInfo getUser(Long id , Integer page ,Integer limit){
        Pageable pageable = PageRequest.of(page-1 , limit);
        Page<User> userPage = deptService.getUser(id, pageable);
        List<User> userList = userPage.getContent();
        List<UserDto> userDtos = userList.stream().map(convertToDto).collect(Collectors.toList());
        PageImpl page1 = (PageImpl) userPage;
        Long total = page1.getTotalElements();
        return ResultInfo.buildSuccess("操作成功", total, userDtos);
    }

    /**
     * 分配员工第一步
     * @return
     */
    @GetMapping ("/getAllUser")
    @ApiOperation("查询所有的员工")
    public ResultInfo listUser(){
        List<UserDto> list =  userService.findAll();
        return ResultInfo.buildSuccess("操作成功",list);
    }

    /**
     * 分配员工第二步
     * @return
     */
    @PostMapping  ("/addUser")
    @ApiOperation("查询所有的员工")
    public ResultInfo addUser(Long deptId, Long[] userId) {
        deptService.addUser(deptId,userId);
        return ResultInfo.buildSuccess("操作成功");
    }

    /**
     * 分配领导
     *
     * @return
     */
    @PostMapping("/addLeader")
    @ApiOperation("分配领导")
    public ResultInfo addLeader(Long deptId, Long userId) {
        deptService.addLeader(deptId, userId);
        return ResultInfo.buildSuccess("操作成功");
    }


    Function<User, UserDto> convertToDto = apply -> {
        UserDto userDto = userMapstruct.convertDto(apply);
        return userDto;
    };


}
