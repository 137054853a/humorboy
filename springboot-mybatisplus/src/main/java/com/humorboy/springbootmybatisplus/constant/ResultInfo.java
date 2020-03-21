package com.humorboy.springbootmybatisplus.constant;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultInfo implements Serializable {

    private static final String SUCCESS = "操作成功";
    private static final String FAILED = "操作失败";

    @NonNull
    private Integer status;
    private String msg;
    private Object data;
    //分页添加查询总数
    private Long total;

    public static ResultInfo buildSuccess() {
        return ResultInfo.builder().status(200).msg(SUCCESS).build();
    }

    public static ResultInfo buildSuccess(Object data) {
        return ResultInfo.builder().status(200).msg(SUCCESS).data(data).build();
    }

    public static ResultInfo buildSuccess(String msg, Object data) {
        return ResultInfo.builder().status(200).msg(msg).data(data).build();
    }

    public static ResultInfo buildSuccess(String msg, Long total, Object data) {
        return ResultInfo.builder().status(200).msg(msg).total(total).data(data).build();
    }

    public static ResultInfo buildFailed() {
        return ResultInfo.builder().status(500).msg(FAILED).build();
    }

    public static ResultInfo buildFailed(String msg) {
        return ResultInfo.builder().status(500).msg(msg).build();
    }
}
