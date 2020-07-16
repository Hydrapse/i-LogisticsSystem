package com.tcsquad.ilogistics.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceMapper {
    //根据表单id名称获取nextid
    Long getFormNextIdByName(String name);

    void increaseSequenceByName(@Param("name") String name, @Param("nextId") Long nextId);

}
