package com.humorboy.system.util;

import com.alibaba.fastjson.JSON;
import lombok.Builder;
import lombok.NonNull;

import java.io.Serializable;

@Builder
public class ReturnResult implements Serializable {

    @NonNull
    private Integer status;
    private String msg;
    private Object data;

    public ReturnResult() {
    }

    public ReturnResult(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
        this.data = null;
    }

    public ReturnResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static ReturnResult success() {
        return new ReturnResult(Integer.valueOf(ResultStatus.SUCCESS.getValue()), ResultStatus.SUCCESS.getReason(), null);
    }

    public static ReturnResult success(String msg, Object data) {
        return ReturnResult.builder().data(data).msg(msg).status(Integer.valueOf(ResultStatus.SUCCESS.getValue())).build();
    }

    public static ReturnResult success(Object data) {
        return new ReturnResult(Integer.valueOf(ResultStatus.SUCCESS.getValue()), ResultStatus.SUCCESS.getReason(), data);
    }

    public static ReturnResult failed() {
        return new ReturnResult(Integer.valueOf(ResultStatus.FAILED.getValue()), ResultStatus.FAILED.getReason(), null);
    }

    public static ReturnResult failed(String msg, Object data) {
        return new ReturnResult(Integer.valueOf(ResultStatus.FAILED.getValue()), msg, data);
    }

    public static ReturnResult failed(Object data) {
        return new ReturnResult(Integer.valueOf(ResultStatus.FAILED.getValue()), ResultStatus.FAILED.getReason(), data);
    }

    public static ReturnResult build(Integer status, String msg, Object data) {
        return new ReturnResult(status, msg, data);
    }

    public static ReturnResult buildAddSucess() {
        return new ReturnResult(Integer.valueOf(ResultStatus.SUCCESS_ADD.getValue()), ResultStatus.SUCCESS_ADD.getReason(), null);
    }

    public static ReturnResult buildAddSucess(Object data) {
        return new ReturnResult(Integer.valueOf(ResultStatus.SUCCESS_ADD.getValue()), ResultStatus.SUCCESS_ADD.getReason(), data);
    }

    public static ReturnResult buildDeleteSucess() {
        return new ReturnResult(Integer.valueOf(ResultStatus.SUCCESS_DELETE.getValue()), ResultStatus.SUCCESS_DELETE.getReason(), null);
    }

    public static ReturnResult buildDeleteSucess(Object data) {
        return new ReturnResult(Integer.valueOf(ResultStatus.SUCCESS_DELETE.getValue()), ResultStatus.SUCCESS_DELETE.getReason(), data);
    }

    public static ReturnResult buildUpdateSucess() {
        return new ReturnResult(Integer.valueOf(ResultStatus.SUCCESS_UPDATE.getValue()), ResultStatus.SUCCESS_UPDATE.getReason(), null);
    }

    public static ReturnResult buildUpdateSucess(Object data) {
        return new ReturnResult(Integer.valueOf(ResultStatus.SUCCESS_UPDATE.getValue()), ResultStatus.SUCCESS_UPDATE.getReason(), data);
    }

    public static ReturnResult buildQuerySucess() {
        return new ReturnResult(Integer.valueOf(ResultStatus.SUCCESS_QUERY.getValue()), ResultStatus.SUCCESS_QUERY.getReason(), null);
    }

    public static ReturnResult buildQuerySucess(Object data) {
        return new ReturnResult(Integer.valueOf(ResultStatus.SUCCESS_QUERY.getValue()), ResultStatus.SUCCESS_QUERY.getReason(), data);
    }

    public static ReturnResult buildAddFailed() {
        return new ReturnResult(Integer.valueOf(ResultStatus.FAILED_ADD.getValue()), ResultStatus.FAILED_ADD.getReason(), null);
    }

    public static ReturnResult buildAddFailed(Object data) {
        return new ReturnResult(Integer.valueOf(ResultStatus.FAILED_ADD.getValue()), ResultStatus.FAILED_ADD.getReason(), data);
    }

    public static ReturnResult buildDeleteFailed() {
        return new ReturnResult(Integer.valueOf(ResultStatus.FAILED_DELETE.getValue()), ResultStatus.FAILED_DELETE.getReason(), null);
    }

    public static ReturnResult buildDeleteFailed(Object data) {
        return new ReturnResult(Integer.valueOf(ResultStatus.FAILED_DELETE.getValue()), ResultStatus.FAILED_DELETE.getReason(), data);
    }

    public static ReturnResult buildUpdateFailed() {
        return new ReturnResult(Integer.valueOf(ResultStatus.FAILED_UPDATE.getValue()), ResultStatus.FAILED_UPDATE.getReason(), null);
    }

    public static ReturnResult buildUpdateFailed(Object data) {
        return new ReturnResult(Integer.valueOf(ResultStatus.FAILED_UPDATE.getValue()), ResultStatus.FAILED_UPDATE.getReason(), data);
    }

    public static ReturnResult buildQueryFailed() {
        return new ReturnResult(Integer.valueOf(ResultStatus.FAILED_QUERY.getValue()), ResultStatus.FAILED_QUERY.getReason(), null);
    }

    public static ReturnResult buildQueryFailed(Object data) {
        return new ReturnResult(Integer.valueOf(ResultStatus.FAILED_QUERY.getValue()), ResultStatus.FAILED_QUERY.getReason(), data);
    }

    public static ReturnResult format(String json) {
        return (ReturnResult) JSON.parseObject(json, ReturnResult.class);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
