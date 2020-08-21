package com.whut.seven.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Zrt
 * @Date 2020/8/18 15:14
 */
@Configuration
public class MyConfig {

    /**
     * @return
     * @Bean 标注在方法上的注解，意思代表标注在哪个方法上，这个方法就被springboot进行管理
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/register.html").setViewName("register");
                registry.addViewController("/login.html").setViewName("login");
                registry.addViewController("/index.html").setViewName("index");
                registry.addViewController("/common.html").setViewName("common");
                registry.addViewController("/pay.html").setViewName("pay");


            }
        };
        return webMvcConfigurer;
    }


}
