package com.tcsquad.ilogistics.mapper.storage;

import com.tcsquad.ilogistics.domain.storage.MainSite;
import com.tcsquad.ilogistics.domain.storage.SubSite;

import java.util.List;

public interface SiteMapper {
    //获取主站信息
    MainSite getMainSiteById(String mainSiteId);

    //获取所有主站信息列表
    List<MainSite> getAllMainSite();

    //获取配送站信息
    SubSite getSubSiteById(String subSiteId);

    //根据主站编号获取配送站列表
    List<SubSite> getSubSiteListByMainSiteId(String mainSiteId);

    //添加配送站
    void insertSubSite(SubSite subSite);

    //注销配送站
    void deleteSubSite(SubSite subSite);

}
