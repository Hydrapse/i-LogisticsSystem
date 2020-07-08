package com.tcsquad.ilogistics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Hydra
 * @date 2020/6/16
 * @description: swagger2配置类
 * 入口: /swagger-ui.html
 */

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tcsquad.ilogistics"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("i-LogisticsSystem")
                .description("A Fully Integrated Logistics System")
                .termsOfServiceUrl("https://github.com/Hydrapse/i-LogisticsSystem")
                .version("version 0.1")
                .build();
    }
}