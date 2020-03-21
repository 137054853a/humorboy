package com.humorboy.system.repository;

import com.humorboy.commons.vo.system.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor {

    //    @Query("From User where id = #{}") hql 面向对象
    User findByUserName(String userName);

    User findByUserNameAndPwd(String userName, String pwd);

    User findByUserId(Long userId);

    Page<User> findAll(Specification query, Pageable page);
//    Page<UserDto> findAll(Specification query, Pageable page);

}
