package com.fz6m.config;


import com.fz6m.servlet.AuthenticationIntercept;
import com.fz6m.servlet.MyIntercept;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyIntercept());

//        registry.addInterceptor(new AuthenticationIntercept())
//                // 拦截所有请求
//                .addPathPatterns("/**")
//                .excludePathPatterns("/user/login");
    }

}
