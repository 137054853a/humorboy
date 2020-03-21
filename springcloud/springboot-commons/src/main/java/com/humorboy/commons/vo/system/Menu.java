package com.humorboy.commons.vo.system;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
//控制menuId 唯一键
@Table(name = "t_menu", uniqueConstraints = {@UniqueConstraint(columnNames = "menuId")})
public class Menu implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private Long menuId;//菜单id
    private Long pId;//父极id  -1 为一级菜单
    private String name;//菜单名称
    private String url;//路径
    private String description;//菜单描述
    private String icon;//图标
    private String orderNum;//排序
    private String createTime;//创建时间


}
