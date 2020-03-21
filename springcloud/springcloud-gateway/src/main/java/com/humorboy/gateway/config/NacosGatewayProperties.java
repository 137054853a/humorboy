package com.humorboy.gateway.config;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "nacos", ignoreUnknownFields = true)
@Configuration
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class NacosGatewayProperties {
    @Value("${nacos.address}")
    private String address;
    @Value("${nacos.data-id}")
    private String dataId;
    @Value("${nacos.group-id}")
    private String groupId;
    @Value("${nacos.timeout}")
    private Long timeout;
}