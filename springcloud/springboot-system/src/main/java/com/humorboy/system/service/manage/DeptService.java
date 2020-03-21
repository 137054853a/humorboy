package com.humorboy.system.service.manage;


import com.humorboy.system.dto.DepartmentDto;
import com.humorboy.system.dto.UserDto;
import com.humorboy.system.repository.UserRepository;
import com.humorboy.system.repository.manage.DeptRespository;
import com.humorboy.system.service.UserService;
import com.humorboy.system.util.DepartmentMapstruct;
import com.humorboy.system.util.UserMapstruct;
import com.humorboy.commons.vo.system.Department;
import com.humorboy.commons.vo.system.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: CC菜
 * @Date: 2019/9/17 14:59
 * @Remark 部门业务层
 */
@Service
public class DeptService {

    @Autowired
    private DeptRespository deptRespository;

    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @Resource
    private DepartmentMapstruct departmentMapstruct;

    @Resource
    private UserMapstruct userMapstruct;

    /**
     * 查询树下所有部门
     *
     * @return
     */
    public DepartmentDto treeListDepartment() {
        Optional<Department> dept = deptRespository.findById(1L);
        Department department = dept.get();
        DepartmentDto dto = departmentMapstruct.convertDto(department);
        Set<User> users = department.getUsers();

        List<Department> children = department.getChildren();
        List<DepartmentDto> departmentDtos = children.stream().map(convertToVo).collect(Collectors.toList());

        List<UserDto> userDtos = users.stream().map(convertToDto).collect(Collectors.toList());
        dto.setUsers(userDtos);
        dto.setChildren(departmentDtos);
        return dto;

    }

    public List<DepartmentDto> listDepartment() {

        List<Department> list = deptRespository.findAll();
        List<DepartmentDto> dto = list.stream().map(convertToVo).collect(Collectors.toList());

        return dto;
    }


    /**
     * 添加部门
     *
     * @param dept
     */
    public void addDept(Department dept) {
        deptRespository.save(dept);
    }

    /**
     * 删除部门
     *
     * @param id
     */
    public void deleteDept(Long id) {
        deptRespository.deleteById(id);
    }

    /**
     * 通过部门Id获取部门详细信息
     *
     * @param id
     * @return
     */
    public Department getDept(Long id) {
        Optional<Department> dept = deptRespository.findById(id);
        return dept.get();
    }

    /**
     * 更新部门信息
     *
     * @param dept
     */
    public void updateDept(Department dept) {
        deptRespository.save(dept);
    }

    /**
     * 根据部门Id查询部门员工
     *
     * @param id
     * @return
     */
    public Page<User> getUser(Long id, Pageable page) {
        Specification query = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                Join<User, Department> join = root.join("departments", JoinType.INNER);
                predicates.add(criteriaBuilder.equal(join.get("id"), id));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Page<User> userPage = userRepository.findAll(query, page);
        return userPage;
        /*List<User> userList =  userPage.getContent();
        List<UserDto> userDtos = userList.stream().map(convertToDto).collect(Collectors.toList());
        return userDtos;*/
    }

    public void addUser(Long deptId, Long[] userId) {
        Optional<Department> optionalDepartment = deptRespository.findById(deptId);
        Department department = optionalDepartment.get();
        Set<User> userSet = department.getUsers();
        for (int i = 0; i < userId.length; i++) {
            Optional<User> optionalUser = userRepository.findById(userId[i]);
            User user = optionalUser.get();
            userSet.add(user);
        }
        department.setUsers(userSet);
        deptRespository.save(department);
    }

    /**
     * 添加部门领导
     *
     * @param deptId
     * @param userId
     */
    public void addLeader(Long deptId, Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        user.setLeader("1");
        userRepository.save(user);
    }


    Function<Department, DepartmentDto> convertToVo = apply -> {
        DepartmentDto departmentDto = departmentMapstruct.convertDto(apply);
        return departmentDto;
    };

    Function<User, UserDto> convertToDto = apply -> {
        UserDto userDto = userMapstruct.convertDto(apply);
        return userDto;
    };
}
