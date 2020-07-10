package com.fz6m.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路径
        registry.addMapping("/**")
                .allowedHeaders("*")  // 允许跨域请求包含任意的头信息
                .allowedOrigins("*")  // 设置允许跨域请求的域名
                .allowCredentials(true) // 是否允许证书 不再默认开启
                .allowedMethods("*") // 设置允许的方法
                .maxAge(3600);      // 跨域允许时间
    }
}
