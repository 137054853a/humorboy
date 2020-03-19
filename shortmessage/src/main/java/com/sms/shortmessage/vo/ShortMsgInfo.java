package com.sms.shortmessage.vo;

import java.io.Serializable;

public class ShortMsgInfo implements Serializable {

    private String phoneNumber;
    private int templateId;
    private String[] params;

    public ShortMsgInfo() {
    }

    public ShortMsgInfo(String phoneNumber, int templateId, String[] params) {
        this.phoneNumber = phoneNumber;
        this.templateId = templateId;
        this.params = params;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }
}
