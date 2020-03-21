package com.humorboy.system.util;

;

public enum ResultStatus {
    SUCCESS(200, "操作成功"),
    SUCCESS_ADD(201, "新增成功"),
    SUCCESS_DELETE(203, "删除成功"),
    SUCCESS_UPDATE(204, "修改成功"),
    SUCCESS_QUERY(205, "查询成功"),
    FAILED(100, "操作失败"),
    FAILED_ADD(101, "新增失败"),
    FAILED_DELETE(102, "删除失败"),
    FAILED_UPDATE(103, "修改失败"),
    FAILED_QUERY(104, "查询失败");
    private int value;
    private String reason;

    private ResultStatus(int value, String reason) {
        this.value = value;
        this.reason = reason;
    }

    public static String getMessage(int value) {
        for (ResultStatus obj : values()) {
            if (obj.getValue() == value) {
                return obj.getReason();
            }
        }
        return null;
    }

    public int getValue() {
        return this.value;
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

    public String toString() {
        return "{value:" + this.value + ", reasonPhrase:'" + this.reason + "'}";
    }
}
