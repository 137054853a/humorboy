package com.humorboy.springbootmybatisplus.constant;

/**
 * @Date 14:48
 * @Author PC028
 */
public enum SystemCode {

    NO_PERMISSION_EXCEPTION(5103, "未授权，请联系管理员");

    private int code;
    private String msg;

    private SystemCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
