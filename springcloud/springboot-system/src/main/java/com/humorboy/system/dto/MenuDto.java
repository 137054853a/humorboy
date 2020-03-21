package com.humorboy.system.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {

    private Long menuId;//菜单id
    private Long pId;//父极id
    private String name;//菜单名称
    private String description;//菜单描述
    private String url;//菜单访问路径
    private String perms;//菜单标识
    private String orderNum;//排序
    private String createTime;//创建时间
    private String id;//用户id


}
