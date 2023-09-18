package com.liu.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Slf4j
//Servlet（控制器）、Filter（过滤器）、Listener（监听器）
// 可以直接通过@WebServlet、@WebFilter、@WebListener注解自动注册到Spring容器中，
// 无需其他代码。
@EnableCaching //开启使用缓存
@ServletComponentScan
@SpringBootApplication
@EnableTransactionManagement
public class RuijiTakeOutApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuijiTakeOutApplication.class, args);

        log.info("项目启动了......");
    }

}
