package com.tcsquad.ilogistics.util;

import com.tcsquad.ilogistics.domain.SequenceName;
import com.tcsquad.ilogistics.mapper.SequenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IDSequenceUtil {
    @Autowired
    SequenceMapper sequenceMapper;

    //使用该接口时应从SequenceName中获取name
    public Long getNextFormIdByName(String name){
        if(SequenceName.isInGroup(name, SequenceName.Group.FormName)){
            Long nextId = sequenceMapper.getFormNextIdByName(name);
            sequenceMapper.increaseSequenceByName(name,nextId+1);
            return nextId;
        }
        else
            return null;
    }
}
