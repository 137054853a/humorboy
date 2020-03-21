package com.humorboy.commons.vo.system;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_role")
public class Role implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private Long roleId;//角色id  超级管理员为"00"
    private String roleName;//角色名称
    private Long isManager;//是否是管理员  0:否 1:是
    private String description;//角色描述
    private String createTime;//创建时间

    // 使用JoinTabl来描述中间表，并描述中间表中外键与Student,Teacher的映射关系
    // joinColumns它是用来描述Student与中间表中的映射关系
    // inverseJoinColums它是用来描述Teacher与中间表中的映射关系
    @ManyToMany(targetEntity = User.class)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "RoleId")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "UserId")})
    private Set<User> users = new HashSet<User>();

    @ManyToMany(targetEntity = Permission.class)
    @JoinTable(name = "role_permission",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "roleId")},
            inverseJoinColumns = {@JoinColumn(name = "permis_code", referencedColumnName = "permisCode")})
    private Set<Permission> permissions = new HashSet<Permission>();
}
