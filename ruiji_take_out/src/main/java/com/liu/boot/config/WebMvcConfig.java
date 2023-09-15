package com.liu.boot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;


/**
 * @Author
 * @Date 2023/9/3 11:20
 * @Description 配置类
 */
@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {


    /**
     * 用于映射静态资源
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {

        log.info("开始静态资源映射......");
        registry.addResourceHandler("/backend/**")
                .addResourceLocations("classpath:/backend/");

        registry.addResourceHandler("/front/**")
                .addResourceLocations("classpath:/front/");
    }
}
