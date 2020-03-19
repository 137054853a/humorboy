package com.sms.shortmessage.vo;

import java.io.Serializable;
import java.util.Arrays;

public class MultiMessageInfo implements Serializable {

    private String[] phoneNumbers;
    private int templateId;
    private String[] params;

    public MultiMessageInfo() {
    }

    public MultiMessageInfo(String[] phoneNumbers, int templateId, String[] params) {
        this.phoneNumbers = phoneNumbers;
        this.templateId = templateId;
        this.params = params;
    }

    public String[] getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String[] phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
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

    @Override
    public String toString() {
        return "MultiMessageInfo{" +
                "phoneNumbers=" + Arrays.toString(phoneNumbers) +
                ", templateId=" + templateId +
                ", params=" + Arrays.toString(params) +
                '}';
    }
}
