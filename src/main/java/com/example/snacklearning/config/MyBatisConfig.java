package com.example.snacklearning.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.snacklearning.mapper")
public class MyBatisConfig {
    // MyBatis 配置类
}