package com.humorboy.commons.vo.system;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_department")
public class Department implements Serializable {

    @Id
    @GeneratedValue
    private Long id;//部门id
    private String title;//部门名称
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="pid")
    private List<Department> children;
    private String description;//部门描述
    private Long pid;//父级部门
    private Date createTime;//录入时间
    private String createBy;//录入人
    private Date updateTime;//更新时间
    private String updateBy;//更新人

    //用户与部门 多对多
    @ManyToMany(targetEntity = User.class)
    @JoinTable(name = "user_department",
            joinColumns = {@JoinColumn(name = "department_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    private Set<User> users = new HashSet<User>();


}
