//package com.mybatis.mybatisplus.generator;
//
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
///**
// * @Date 10:46
// * @Author PC028
// */
////Spring boot方式
//@EnableTransactionManagement
//@Configuration
//@MapperScan("com.baomidou.cloud.service.*.mapper*")
//public class MybatisPlusConfig {
//
//    /**
//     * SQL执行效率插件
//     */
//    @Bean
//    @Profile({"dev","test"})// 设置 dev test 环境开启
//    public PerformanceInterceptor performanceInterceptor() {
//        return new PerformanceInterceptor();
//    }
//}