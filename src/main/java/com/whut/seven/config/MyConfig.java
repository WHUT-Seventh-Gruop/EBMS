package com.whut.seven.config;

import com.whut.seven.interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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
            /**
             * Add Spring MVC lifecycle interceptors for pre- and post-processing of
             * controller method invocations and resource handler requests.
             * Interceptors can be registered to apply to all requests or be limited
             * to a subset of URL patterns.
             *
             * @param registry
             */
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/login.html","/register.html", "/", "/admin-login.html","/back/login","/login/login1","/login/register","/signupsuccess.html","/user/checkName")
                        .excludePathPatterns("/css/**")
                        .excludePathPatterns("/images/**")
                        .excludePathPatterns("/js/**")
                        .excludePathPatterns("/lib/**")
                        .excludePathPatterns("/fonts/**");
            }

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/register.html").setViewName("register");
                registry.addViewController("/login.html").setViewName("login");
                registry.addViewController("/index.html").setViewName("index");
                registry.addViewController("/common.html").setViewName("common");
                registry.addViewController("/pay.html").setViewName("pay");
                registry.addViewController("/billsearch.html").setViewName("billsearch");
                registry.addViewController("/billsearch1.html").setViewName("billsearch1");
                registry.addViewController("/paymentplatform.html").setViewName("paymentplatform");
                registry.addViewController("paysuccess.html").setViewName("paysuccess");
                registry.addViewController("/payhistory.html").setViewName("payhistory");
                registry.addViewController("updatepassword.html").setViewName("updatepassword");
                registry.addViewController("signupsuccess.html").setViewName("signupsuccess");

                registry.addViewController("admin-bill.html").setViewName("admin/bill");
                registry.addViewController("admin-unit.html").setViewName("admin/unit");

                registry.addViewController("admin-users.html").setViewName("admin/users");
                registry.addViewController("admin-users-add.html").setViewName("admin/users-add");
                registry.addViewController("admin-users-upd.html").setViewName("admin/users-upd");

                registry.addViewController("admin-elec.html").setViewName("admin/elec");
                registry.addViewController("admin-login.html").setViewName("admin/login");
                registry.addViewController("admin-bill-upd.html").setViewName("admin/bill-upd");
                registry.addViewController("admin-password.html").setViewName("admin/password");
                registry.addViewController("admin-bill-add.html").setViewName("admin/bill-add");
                registry.addViewController("admin-unit-add.html").setViewName("admin/unit-add");
                registry.addViewController("admin-unit-upd.html").setViewName("admin/unit-upd");
            }
        };
        return webMvcConfigurer;
    }


}
