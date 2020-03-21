package com.humorboy.system.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
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
    private String leader;//是否领导 ，1领导
    private int page; //当前页
    private int limit;//每页条数


}
