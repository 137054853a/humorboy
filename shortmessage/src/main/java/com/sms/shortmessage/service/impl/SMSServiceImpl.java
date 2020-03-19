package com.sms.shortmessage.service.impl;

import com.github.qcloudsms.SmsMultiSenderResult;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.sms.shortmessage.config.SMSConfig;
import com.sms.shortmessage.service.SMSService;
import com.sms.shortmessage.util.SMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SMSServiceImpl implements SMSService {
    @Autowired
    private SMSConfig config;

    @Override
    public SmsSingleSenderResult sendMessage(String phoneNumber, int templateId, String[] params) {//getProperty("sms.appid");
        String[] phoneNumbers = new String[]{phoneNumber};
        SmsSingleSenderResult result = SMSUtils.getInstance().sendMesage(Integer.parseInt(config.getAppId()), config.getAppKey(), phoneNumbers, templateId, params, "");
        return result;
    }

    @Override
    public SmsMultiSenderResult sendMultiMessage(String[] phoneNumbers, int templateId, String[] params) {
        SmsMultiSenderResult result = SMSUtils.getInstance().smsMultiSend(Integer.parseInt(config.getAppId()), config.getAppKey(), phoneNumbers, templateId, params, "");
        return result ;
    }
}
