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
@Table(name = "t_permission")
public class Permission implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private Long permisCode; //权限编码
    private String permisName; //权限名称
    private Long menuId; //菜单id
    private Date createTime; //创建时间

    @ManyToMany(targetEntity = Role.class)
    @JoinTable(name = "role_permission",
            joinColumns = {@JoinColumn(name = "permis_code", referencedColumnName = "permisCode")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "roleId")})
    private Set<Role> roles = new HashSet<Role>();

}
