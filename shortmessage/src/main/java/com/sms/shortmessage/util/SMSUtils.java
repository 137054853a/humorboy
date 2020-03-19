package com.sms.shortmessage.util;

import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class SMSUtils {
    private static SMSUtils utils = null;
    private static Logger logger = LoggerFactory.getLogger(SMSUtils.class);
    public static synchronized SMSUtils getInstance() {
        if (utils == null) {
            utils = new SMSUtils();
        }
        return utils;
    }

   /**  短信应用SDK AppID
    int appid = 1400150518; // 1400开头
     短信应用SDK AppKey
    String appkey = "5a97ebd67ed297acc9d51435d6669381";
     需要发送短信的手机号码
    String[] phoneNumbers = {"18161256960"};
     短信模板ID，需要在短信应用中申请
    int templateId = 2107871; // NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请
    templateId7839对应的内容是"您的验证码是: {1}"
     签名
    String smsSign = "饿龙谷"; // NOTE: 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，另外签名参数使用的是`签名内容`，而不是`签名ID`*/
    public SmsSingleSenderResult sendMesage(int appid, String appkey, String[] phoneNumbers, int templateId, String[] params, String smsSign) {
        try {
            /**String[] params = {"5678"};//数组具体的元素个数和模板中变量个数必须一致，例如事例中templateId:5678对应一个变量，参数数组中元素个数也必须是一个*/
            logger.debug("电话:{},使用模板id:{}",phoneNumbers[0],templateId);
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            /** 签名参数未提供或者为空时，会使用默认签名发送短信*/
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumbers[0], templateId, params, smsSign, "", "");
            logger.debug("发送结果:{}",result);
            return result;
        } catch (HTTPException e) {
            // HTTP响应码错误
            logger.error("HTTP响应码错误",e);
        } catch (JSONException e) {
            // json解析错误
            logger.error("json解析错误",e);
        } catch (IOException e) {
            // 网络IO错误
            logger.error("网络IO错误",e);
        }
        return null;
    }

    public SmsMultiSenderResult smsMultiSend(int appid, String appkey, String[] phoneNumbers, int templateId, String[] params, String smsSign) {
        try {
            /** String[] params = {"5678"};//数组具体的元素个数和模板中变量个数必须一致，例如事例中templateId:5678对应一个变量，参数数组中元素个数也必须是一个*/
            SmsMultiSender msender = new SmsMultiSender(appid, appkey);
            /** 签名参数未提供或者为空时，会使用默认签名发送短信*/
            logger.debug("电话:{},使用模板id:{}",phoneNumbers,templateId);
            SmsMultiSenderResult result = msender.sendWithParam("86", phoneNumbers,
                    templateId, params, smsSign, "", "");
            logger.debug("发送结果:{}",result);
            return result;
        } catch (HTTPException e) {
            // HTTP响应码错误
            logger.error("HTTP响应码错误",e);
        } catch (JSONException e) {
            // json解析错误
            logger.error("json解析错误",e);
        } catch (IOException e) {
            // 网络IO错误
            logger.error("网络IO错误",e);
        }
        return null;
    }
}
