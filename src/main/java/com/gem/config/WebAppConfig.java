package com.gem.config;

import com.gem.interceptor.MyAdminInterceptor1;
import com.gem.interceptor.MyAdminInterceptor2;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: dudulu
 * @Email: mail@dudulu.net
 * @Description: 拦截器
 * @Date: 2020-07-09
 **/
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    //实现拦截器 要拦截的路径以及不拦截的路径
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //注册自定义拦截器，添加拦截路径和排除拦截路径

        registry.addInterceptor(new MyAdminInterceptor1())
                .addPathPatterns("/user/admin/**");
        registry.addInterceptor(new MyAdminInterceptor2())
                .addPathPatterns("/user/u_index");
        registry.addInterceptor(new MyAdminInterceptor2())
                .addPathPatterns("/user/u_profile");
        registry.addInterceptor(new MyAdminInterceptor2())
                .addPathPatterns("/user/change_user");


        registry.addInterceptor(new MyAdminInterceptor1())
                .addPathPatterns("/information/admin/**");
        registry.addInterceptor(new MyAdminInterceptor2())
                .addPathPatterns("/information/**");

        registry.addInterceptor(new MyAdminInterceptor1())
                .addPathPatterns("/attendance/admin/**");
        registry.addInterceptor(new MyAdminInterceptor2())
                .addPathPatterns("/attendance/**");

        registry.addInterceptor(new MyAdminInterceptor1())
                .addPathPatterns("/leave/admin/**");
        registry.addInterceptor(new MyAdminInterceptor2())
                .addPathPatterns("/leave/**");

        registry.addInterceptor(new MyAdminInterceptor1())
                .addPathPatterns("/announcement/admin/**");
        registry.addInterceptor(new MyAdminInterceptor2())
                .addPathPatterns("/announcement/**");

        registry.addInterceptor(new MyAdminInterceptor1())
                .addPathPatterns("/wageinfo/admin/**");
        registry.addInterceptor(new MyAdminInterceptor2())
                .addPathPatterns("/wageinfo/**");
    }
}