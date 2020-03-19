package com.sms.shortmessage.service;

import com.github.qcloudsms.SmsMultiSenderResult;
import com.github.qcloudsms.SmsSingleSenderResult;

public interface SMSService {
    public SmsSingleSenderResult sendMessage(String phoneNumber, int templateId, String[] params);

    public SmsMultiSenderResult sendMultiMessage(String[] phoneNumbers, int templateId, String[] params);

}
