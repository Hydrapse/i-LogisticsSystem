package com.tcsquad.ilogistics.service;

import com.tcsquad.ilogistics.mapper.storage.SiteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SiteService {
    @Autowired
    SiteMapper siteMapper;

    public List<String> getAllSubSite() {
        var result = new ArrayList<String>();
        var mainSites = siteMapper.getAllMainSite();
        for(var mainSite:mainSites) {
            var subSites = siteMapper.getSubSiteListByMainSiteId(mainSite.getMainsiteId());
            for(var subSite:subSites) {
                result.add(subSite.getSubsiteId());
            }
        }
        return result;
    }

    public List<String> getAllMainSite() {
        var result = new ArrayList<String>();
        var mainSites = siteMapper.getAllMainSite();
        for(var mainSite:mainSites) {
            result.add(mainSite.getMainsiteId());
        }
        return result;
    }
}
