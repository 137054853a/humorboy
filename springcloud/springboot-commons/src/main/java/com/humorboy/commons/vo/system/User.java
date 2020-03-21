package com.humorboy.commons.vo.system;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_user")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column(insertable = false, updatable = false, nullable = false, columnDefinition = "bigint(20) not null UNIQUE key auto_increment")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;//登录名
    private String pwd; //密码
    private String name;  //真实姓名
    private String age;  //年龄
    private String sex; //性别
    private Integer phone;//联系电话
    private String email; //邮箱
    private Integer status; //用户状态 1.有效 0.无效
    private Date createTime;//创建时间
    private Date updateTime;//创建时间
    private String leader; //是否领导 ，1领导


    //用户与角色多对多
    @ManyToMany(targetEntity = Role.class)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "roleId")})
    private Set<Role> roles = new HashSet<Role>();
    //用户与部门 多对多
    @ManyToMany(targetEntity = Department.class)
    @JoinTable(name = "user_department",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "department_id", referencedColumnName = "id")})
    private Set<Department> departments = new HashSet<Department>();

}
