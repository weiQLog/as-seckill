package com.as.seckill.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.as.seckill.dao"})
public class MybatisConfig {
}
