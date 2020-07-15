package com.tcsquad.ilogistics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Enzo Cotter on 2020/1/21.
 */
@Configuration
public class MySpringMvcConfigurer{

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {

            //添加视图控制器
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("imageUpload");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
//                registry.addInterceptor(new LoginHandlerInterceptor())
//                        //指定要拦截的请求
//                        .addPathPatterns("/**")
//                        //排除不需要拦截的路径
//                        .excludePathPatterns("/", "/index.html", "/login")
//                        //排除资源路径
//                        .excludePathPatterns("/css/*", "/img/*", "/js/*");
            }

            //资源拦截器
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/**")
                        .addResourceLocations("classpath:/META-INF/resources/")
                        .addResourceLocations("classpath:/resources")
                        .addResourceLocations("classpath:/static/")
                        .addResourceLocations("classpath:/css");
            }
        };
    }

}
