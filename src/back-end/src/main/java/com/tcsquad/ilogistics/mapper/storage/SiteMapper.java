package com.tcsquad.ilogistics.mapper.storage;

import com.tcsquad.ilogistics.domain.storage.MainSite;
import com.tcsquad.ilogistics.domain.storage.SubSite;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SiteMapper {
    //获取主站信息
    MainSite getMainSiteById(String mainsiteId);

    //获取所有主站信息列表
    List<MainSite> getAllMainSite();

    //获取配送站信息
    SubSite getSubSiteById(String subsiteId);

    //根据主站编号获取配送站列表
    List<SubSite> getSubSiteListByMainSiteId(String mainsiteId);

    //添加配送站
    void insertSubSite(SubSite subSite);

    //注销配送站
    void deleteSubSite(SubSite subSite);

}
