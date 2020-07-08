package com.tcsquad.ilogistics.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    //绑定数据源配置，标明前缀 
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }

    //配置druid后台管理servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){

        //注意，请求是"/druid/*"
        ServletRegistrationBean<StatViewServlet> bean =
                new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        Map<String, String> initParam = new HashMap<>();
        initParam.put(StatViewServlet.PARAM_NAME_USERNAME, "admin");
        initParam.put(StatViewServlet.PARAM_NAME_PASSWORD, "123");
        initParam.put(StatViewServlet.PARAM_NAME_ALLOW, ""); //允许访问的IP，不填就是所有
        bean.setInitParameters(initParam);

        return bean;
    }

    //配置druid过滤器
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>(new WebStatFilter());

        Map<String, String> initParam = new HashMap<>();
        initParam.put(WebStatFilter.PARAM_NAME_EXCLUSIONS, "*.js,*.css,/druid/*"); //不需要过滤的路径
        bean.setInitParameters(initParam);

        //设置拦截请求
        bean.setUrlPatterns(Arrays.asList("/*"));

        return bean;
    }

}
