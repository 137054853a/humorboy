package com.humorboy.system.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: CC菜
 * @Date: 2019/9/23 16:32
 * @Remark
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    private Long id;//部门id
    private String title;//部门名称
    private String description;//部门描述
    private Long pid;//父级部门
    private List<DepartmentDto> children;

    private List<UserDto> users = new ArrayList<UserDto>();
}
