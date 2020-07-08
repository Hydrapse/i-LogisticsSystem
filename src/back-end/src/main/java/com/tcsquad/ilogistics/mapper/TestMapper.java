package com.tcsquad.ilogistics.mapper;

import org.springframework.stereotype.Repository;

@Repository
public interface TestMapper {

    String getTestNameByTestId(String testId);

    //如果有多个复杂参数使用@Param("xxx")

}
