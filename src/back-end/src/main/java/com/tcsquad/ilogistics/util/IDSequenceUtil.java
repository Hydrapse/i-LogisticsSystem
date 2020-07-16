package com.tcsquad.ilogistics.util;

import com.tcsquad.ilogistics.domain.SequenceName;
import com.tcsquad.ilogistics.mapper.SequenceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;

@Component
public class IDSequenceUtil {
    private static Logger logger = LoggerFactory.getLogger(IDSequenceUtil.class);

    @Autowired
    SequenceMapper sequenceMapper;

    private static String WAREHOUSE_PREFIX = "WH-";
    private static String MAINSITE_PREFIX = "MAIN-";
    private static String SUBSITE_PREFIX = "SUB-";
    private static String SUPPLIER_PREFIX = "SUP-";

    //使用该接口时应从SequenceName中获取name
    @Transactional
    public Long getNextFormIdByName(String name){
        if(SequenceName.isInGroup(name, SequenceName.Group.FormName)){
            Long nextId = sequenceMapper.getFormNextIdByName(name);
            sequenceMapper.increaseSequenceByName(name,nextId+1);
            return nextId;
        }
        else
            return null;
    }

    @Transactional
    public String getNextSubsiteId(){
        long nextSequence = sequenceMapper.getFormNextIdByName(SequenceName.SUBSITEID.getValue());
        sequenceMapper.increaseSequenceByName(SequenceName.SUBSITEID.getValue(),nextSequence+1);
        DecimalFormat df = new DecimalFormat("000");
        String str = df.format(nextSequence);
        String nextId = SUBSITE_PREFIX + str;
        logger.info(nextId);

        return nextId;
    }

    @Transactional
    public String getNextMainsiteId(){
        long nextSequence = sequenceMapper.getFormNextIdByName(SequenceName.MAINSITEID.getValue());
        sequenceMapper.increaseSequenceByName(SequenceName.MAINSITEID.getValue(),nextSequence+1);
        DecimalFormat df = new DecimalFormat("000");
        String str = df.format(nextSequence);
        String nextId = MAINSITE_PREFIX + str;
        logger.info(nextId);

        return nextId;
    }

    @Transactional
    public String getNextSupplierId(){
        long nextSequence = sequenceMapper.getFormNextIdByName(SequenceName.SUPPLIERID.getValue());
        sequenceMapper.increaseSequenceByName(SequenceName.SUPPLIERID.getValue(),nextSequence+1);
        DecimalFormat df = new DecimalFormat("000");
        String str = df.format(nextSequence);
        String nextId = SUPPLIER_PREFIX + str;
        logger.info(nextId);

        return nextId;
    }

    @Transactional
    public String getNextWarehouseId(){
        long nextSequence = sequenceMapper.getFormNextIdByName(SequenceName.WAREHOUSEID.getValue());
        sequenceMapper.increaseSequenceByName(SequenceName.WAREHOUSEID.getValue(),nextSequence+1);
        DecimalFormat df = new DecimalFormat("000");
        String str = df.format(nextSequence);
        String nextId = WAREHOUSE_PREFIX + str;
        logger.info(nextId);

        return nextId;
    }

    @Transactional
    public String getNextItemId(String categoryId){
        if(!SequenceName.isInGroup(categoryId,SequenceName.Group.CategoryId)){
            return null;
        }

        String prefix = categoryId.substring(0,1).toUpperCase() + "-";
        long nextSequence = sequenceMapper.getFormNextIdByName(categoryId);
        sequenceMapper.increaseSequenceByName(categoryId,nextSequence+1);
        DecimalFormat df = new DecimalFormat("000");
        String str = df.format(nextSequence);
        String nextId = prefix + str;
        logger.info(nextId);
        return nextId;
    }


}
