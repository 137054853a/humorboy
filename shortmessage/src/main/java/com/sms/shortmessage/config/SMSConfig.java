package com.sms.shortmessage.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 加载properties配置文件,在方法中可以获取
 * abc.properties文件不存在,验证ignoreResourceNotFound属性
 * 加上encoding = "utf-8"属性防止中文乱码,不能为大写的"UTF-8"
 * Created by sun on 2017-3-30.
 */
@Data
@Component
@ConfigurationProperties(prefix = "sms")
public class SMSConfig {
    private String appId;
    private String appKey;
    private String smsSign;
}
