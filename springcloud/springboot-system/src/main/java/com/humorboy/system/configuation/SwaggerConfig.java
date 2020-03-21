package com.humorboy.system.configuation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(appInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zshq.system.controller"))
                .paths(PathSelectors.any()).build();
    }

    /**
     * About our API
     *
     * @return
     */
    private ApiInfo appInfo() {
        return new ApiInfoBuilder()
                // //大标题
                .title("公司OA系统 API")
                // 版本号
                .version("1.0")
                // 描述
                .description("公司OA系统 API")
                //作者
                .contact(new Contact("", "http://www.baidu.com/", ""))
                .build();
    }
}
