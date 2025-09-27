package com.heima.springai_study.config;


import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

@MapperScan("com.heima.springai_study.mapper")
@Slf4j
public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        return interceptor;
    }
}