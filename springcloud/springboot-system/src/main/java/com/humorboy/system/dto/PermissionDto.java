package com.humorboy.system.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDto {

    private Long id;
    private String permisCode; //权限编码
    private String permisName; //权限名称
    private String menuId; //菜单id


}
