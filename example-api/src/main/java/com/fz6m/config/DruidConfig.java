package com.fz6m.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }

    /**
     * 配置一个druid的监控
     * 1. 配置一个druid的后台 管理servlet
     * 2. 配置一个druid的filter
     *
     */
    // 1. 配置一个druid的后台管理servlet
    @Bean
    public ServletRegistrationBean druidServlet() {
        //注意：请求是 /druid/*
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean
                (new StatViewServlet(), "/druid/*");
        // 白名单,多个用逗号分割， 如果allow没有配置或者为空，则允许所有访问
//         servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        // 黑名单,多个用逗号分割 (共同存在时，deny优先于allow)
        // servletRegistrationBean.addInitParameter("deny", "192.168.1.110");
        // 控制台管理用户名
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        // 控制台管理密码
        servletRegistrationBean.addInitParameter("loginPassword", "password");
        // 是否可以重置数据源，禁用HTML页面上的“Reset All”功能
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean ;

    }


    //2. 配置一个druid的filter
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean() ;
        filterRegistrationBean.setFilter(new WebStatFilter());
        //所有请求进行监控处理
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.css,/druid/*");
        return filterRegistrationBean ;
    }
}
