package com.sms.shortmessage.vo;

import lombok.Data;

@Data
public class ResultInfo {
    private Integer code;
    private Integer status;
    private String msg;

    public ResultInfo() {
        this.msg = "success!";
        this.status = 200;
    }

    public ResultInfo(Integer status) {
        this.status = status;
    }
}
