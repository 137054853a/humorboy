package com.humorboy.commons.constant;

public enum ReturnCode {

    SUCCESS(200, "操作成功"),
    FAILED(100, "操作失败");

    private int value;
    private String reason;

    ReturnCode(int value, String reason) {
        this.value = value;
        this.reason = reason;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "ReturnCode{" +
                "value=" + value +
                ", reason='" + reason + '\'' +
                '}';
    }
}
