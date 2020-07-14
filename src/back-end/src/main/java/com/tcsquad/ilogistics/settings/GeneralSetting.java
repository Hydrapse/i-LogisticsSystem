package com.tcsquad.ilogistics.settings;

/**
 * @author Hydra
 * @date 2020/7/13
 * @description: 通用配置类, 为所有配置类的父类
 */

public class GeneralSetting {
    //是否启用配置
    private boolean available = true;

    /**
     * 功能描述:<br>
     * 配置是否生效, 需要进行判断
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * 功能描述:<br>
     * 启用配置
     */
    public void enableSetting(){
        available = true;
    }

    /**
     * 功能描述:<br>
     * 关闭配置
     */
    public void disableSetting(){
        available = false;
    }

}
