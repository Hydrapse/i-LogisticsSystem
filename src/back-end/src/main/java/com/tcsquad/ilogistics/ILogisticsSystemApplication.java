package com.tcsquad.ilogistics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAsync
@EnableTransactionManagement
@EnableSwagger2
@MapperScan("com.tcsquad.ilogistics.mapper")
@SpringBootApplication
public class ILogisticsSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ILogisticsSystemApplication.class, args);
    }

}
