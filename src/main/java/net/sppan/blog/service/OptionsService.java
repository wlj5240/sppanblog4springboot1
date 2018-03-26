package net.sppan.blog.service;

import java.util.List;

import net.sppan.blog.entity.Options;


public interface OptionsService {


    String SITE_ABOUTME = "siteAboutMe";
    String SITE_NAME = "siteName";
    String SITE_DESCRIPTION = "siteDescription";
    String SITE_ICP = "siteIcp";
    String SITE_BANNER = "siteBanner";
    String SITE_NOTICE = "siteNotice";

    List<Options> findAll();

    /**
     * 设置关于我
     *
     * @param content
     */
    void saveAboutMe(String content);
    String findAboutMe();

    String findIcp();
    void saveIcp(String icp);

    String findDescription();
    void saveDescription(String desc);

    List<String> findBanner();
    void saveBanner(String banner);

    String findNotice();
    void saveNotice(String notice);

    String findSiteName();
    void saveSiteName(String siteName);
}
