package com.lau56.lease.web.admin.custom.config;

import com.lau56.lease.web.admin.custom.converter.StringToBaseEnumConverterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Michael
 * @date 2025/6/6 23:57
 * @description WebMvcConfiguration配置类
 */

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    // 这里可以添加其他的WebMvc配置，例如拦截器、视图解析器等

    @Autowired
    private StringToBaseEnumConverterFactory stringToBaseEnumConverterFactory;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 注册自定义的字符串到ItemType的转换器
        registry.addConverterFactory(this.stringToBaseEnumConverterFactory);


    }
}
