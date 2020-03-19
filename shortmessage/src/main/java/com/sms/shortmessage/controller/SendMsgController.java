package com.sms.shortmessage.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.sms.shortmessage.exception.MessageSendException;
import com.sms.shortmessage.service.SMSService;
import com.sms.shortmessage.vo.MultiMessageInfo;
import com.sms.shortmessage.vo.ResultInfo;
import com.sms.shortmessage.vo.ShortMsgInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMsgController {
    private static Logger logger = LoggerFactory.getLogger(SendMsgController.class);

    @Autowired
    private SMSService smsService;

    @PostMapping("/sf")
    public ResultInfo sendMessage(@RequestBody String phoneNumber) {
        logger.debug("sendMessage=======>{}",phoneNumber);
        ShortMsgInfo msgInfo = JSONObject.parseObject(phoneNumber, ShortMsgInfo.class);
        SmsSingleSenderResult result = smsService.sendMessage(msgInfo.getPhoneNumber(), msgInfo.getTemplateId(), msgInfo.getParams());
        if (result.result != 0) {
            logger.error(result.toString());
            throw new MessageSendException(result.toString());
        }
        return new ResultInfo();
    }
    @RequestMapping("/mf")
    public ResultInfo sendMultiMessage(@RequestBody String messageInfo ) {
        logger.debug("sendMessage=======>{}",messageInfo);
        MultiMessageInfo msgInfo = JSONObject.parseObject(messageInfo, MultiMessageInfo.class);
        //ShortMsgInfo msgInfo = JSONObject.parseObject(messageInfo, ShortMsgInfo.class);
        SmsMultiSenderResult result = smsService.sendMultiMessage(msgInfo.getPhoneNumbers(), msgInfo.getTemplateId(), msgInfo.getParams());
        if (result.result != 0) {
            logger.error(result.toString());
            throw new MessageSendException(result.toString());
        }
        return new ResultInfo();
    }
}
