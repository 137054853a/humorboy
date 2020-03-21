package com.humorboy.system.service;

import com.humorboy.system.dto.UserDto;
import com.humorboy.system.repository.UserRepository;
import com.humorboy.system.util.UserMapstruct;
import com.humorboy.commons.vo.system.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Resource
    UserMapstruct userMapstruct;

//    @HystrixCommand(fallbackMethod = "fail", commandKey = "getUserById", groupKey = "UserGroup",
//            threadPoolKey = "getUserByIdThread")
//    public User findById(Long id) {
//        /*Optional<User> user = userRepository.findById(id);
//        return user.get();*/
//        return null;
//    }

    /**
     * 新增时查询用户名是否已存在
     *
     * @param userName
     * @return
     */
    public User findByUserName(String userName) {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            return null;
        }
        return user;
    }

    protected User fail(Long id) {
        return new User();
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    public List<UserDto> findAll() {
        List<User> userList = userRepository.findAll();
        List<UserDto> collect = userList.stream().map(convertovo).collect(Collectors.toList());
        return collect;
    }

    public boolean update(UserDto userDto) {
        //封装对象
        User user = User.builder().id(userDto.getId()).userId(userDto.getUserId()).userName(userDto.getUserName())
                .pwd(userDto.getPwd())
                .name(userDto.getName())
                .age(userDto.getAge())
                .sex(userDto.getSex())
                .phone(userDto.getPhone())
                .email(userDto.getEmail())
                .status(userDto.getStatus())
                .updateTime(new Date()).build();
        try {
            User uUser = userRepository.save(user);
        } catch (Exception e) {
            log.error("修改失败！", e.getMessage());
            return false;
        }
        return true;
    }


    /**
     * 批量新增
     *
     * @param userList
     * @return
     */
    public List<User> saveList(List<User> userList) {
        List<User> list = userRepository.saveAll(userList);
        return list;
    }

    /**
     * 新增单个用户
     *
     * @param
     * @return
     */
    public boolean save(UserDto userDto) {
        try {
            //封装实体
            User user = User.builder().userName(userDto.getUserName())
                    .pwd(userDto.getPwd())
                    .name(userDto.getName())
                    .age(userDto.getAge())
                    .sex(userDto.getSex())
                    .phone(userDto.getPhone())
                    .email(userDto.getEmail())
                    .createTime(new Date()).build();
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            log.error("新增失败", e.getMessage());
            return false;
        }
    }
    /**
     * 认证查看用户是否存在
     *
     * @param userName
     * @param pwd
     * @return
     */
    public User findByUserNamePwd(String userName, String pwd) {
        User user = userRepository.findByUserNameAndPwd(userName, pwd);
        return user;
    }

    /**
     * 分页查询
     *
     * @param userDto
     * @return
     */
    public Map<String, Object> listUser(UserDto userDto) {
        Map<String, Object> map = new HashMap<>();
        Specification query = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                //根据用户名进行模糊查询
                if (StringUtils.isNotBlank(userDto.getUserName())) {
                    predicates.add(criteriaBuilder.like(root.get("userName"), "%" + userDto.getUserName() + "%"));
                }
                //根据用户id进行模糊查询
                if (userDto.getUserId() != null) {
                    predicates.add(criteriaBuilder.like(root.get("userId"), "%" + userDto.getUserId() + "%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        //页码：前端从1开始，jpa从0开始 此处减1
        Pageable page = PageRequest.of(userDto.getPage() - 1, userDto.getLimit());
        Page<User> userPage = userRepository.findAll(query, page);
        List<UserDto> collect = userPage.stream().map(convertovo).collect(Collectors.toList());
        map.put("total", userPage.getTotalElements());
        map.put("data", collect);
        return map;
    }

    Function<User, UserDto> convertovo = apply -> {
        UserDto userDto = userMapstruct.convertDto(apply);
        return userDto;
    };


    /**
     * 删除一个
     *
     * @param id
     * @return
     */
    public Boolean deleteOne(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("删除失败！");
            return false;
        }
    }


}
