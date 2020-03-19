package com.sms.shortmessage.exceptionHandler;

import com.alibaba.fastjson.JSONObject;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.sms.shortmessage.exception.MessageSendException;
import com.sms.shortmessage.exception.NullArgumentsException;
import com.sms.shortmessage.exception.TemplateException;
import com.sms.shortmessage.vo.ResultInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MessageSendException.class)
    @ResponseBody
    public ResultInfo sessionNotFoundExceptionHandler(MessageSendException exception) throws Exception {
        ResultInfo info = new ResultInfo(500);
        String message = exception.getMessage();
        SmsSingleSenderResult result = JSONObject.parseObject(message, SmsSingleSenderResult.class);
        info.setCode(result.result);
        info.setMsg(result.errMsg);
        return info;
    }

    @ExceptionHandler(NullArgumentsException.class)
    @ResponseBody
    public ResultInfo nullOrEmptyExceptionHandler(NullArgumentsException exception) throws Exception {
        ResultInfo info = new ResultInfo();
        return info;
    }

    @ExceptionHandler(TemplateException.class)
    @ResponseBody
    public ResultInfo illegalPropExceptionHandler(TemplateException exception) throws Exception {
        ResultInfo info = new ResultInfo();
        return info;
    }

}