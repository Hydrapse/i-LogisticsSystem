package com.tcsquad.ilogistics.settings;

import org.springframework.stereotype.Component;

@Component
public class SiteOutSetting extends GeneralSetting{
    //取货策略
    /**
     * 出库的库房选择
     * --选项一：默认从单个库房出库，单个库房存货不足时剩余量重新分拣出库
     * --选项二：默认从单个库房出库，单个库房存货不足时按比例从多个库房分批出库
     * --选项三：默认从单个库房出库，到达一定值时按比例从多个库房分批出库
     */

    private static final int option1 = 1;
    private static final int option2 = 2;
    private static final int option3 = 3;
    private int option;
    private int threshold;

    public SiteOutSetting(){
       selectOption1();
       this.threshold = 0;
    }

    public void selectOption1(){
        option = option1;
    }

    public void selectOption2(){
        option =option2;
    }

    public void selectOption3(){
        option = option3;
    }

    public int getOption(){
        return option;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
