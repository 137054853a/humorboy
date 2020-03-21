package com.humorboy.commons.constant;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultInfo implements Serializable {

    @NonNull
    private Integer status;
    private String msg;
    private Object data;
    //分页添加查询总数
    private Long total;

    public static ResultInfo buildSuccess(String msg, Object data) {
        return ResultInfo.builder().status(ReturnCode.SUCCESS.getValue()).msg(msg).data(data).build();
    }

    public static ResultInfo buildSuccess(String msg, Long total, Object data) {
        return ResultInfo.builder().status(ReturnCode.SUCCESS.getValue()).msg(msg).total(total).data(data).build();
    }

    public static ResultInfo buildSuccess(Object data) {
        return ResultInfo.builder().status(ReturnCode.SUCCESS.getValue()).msg(ReturnCode.SUCCESS.getReason()).data(data).build();
    }

    public static ResultInfo buildSuccess() {
        return ResultInfo.builder().status(ReturnCode.SUCCESS.getValue()).msg(ReturnCode.SUCCESS.getReason()).build();
    }

    public static ResultInfo buildFailed(String msg, Object data) {
        return ResultInfo.builder().status(ReturnCode.FAILED.getValue()).msg(msg).data(data).build();
    }

    public static ResultInfo buildFailed(Object data) {
        return ResultInfo.builder().status(ReturnCode.FAILED.getValue()).msg(ReturnCode.FAILED.getReason()).data(data).build();
    }

    public static ResultInfo buildFailed() {
        return ResultInfo.builder().status(ReturnCode.FAILED.getValue()).msg(ReturnCode.FAILED.getReason()).build();
    }

    public static ResultInfo build(Integer code, String msg, Object data) {
        return ResultInfo.builder().status(code).msg(msg).data(data).build();
    }
}
