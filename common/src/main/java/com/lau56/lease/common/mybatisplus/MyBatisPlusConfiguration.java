package com.lau56.lease.common.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Michael
 * @date 2025/6/6 00:40
 * @description MyBatisPlus配置类
 */

@Configuration
@MapperScan("com.lau56.lease.web.*.mapper")
public class MyBatisPlusConfiguration {
}
