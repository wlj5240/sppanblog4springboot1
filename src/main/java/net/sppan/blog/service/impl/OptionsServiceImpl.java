package net.sppan.blog.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import net.sppan.blog.entity.Options;
import net.sppan.blog.repository.OptionsRepository;
import net.sppan.blog.service.OptionsService;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class OptionsServiceImpl implements OptionsService {

    @Resource
    private OptionsRepository optionsRepository;

    @Override
    public List<Options> findAll() {
        return optionsRepository.findAll();
    }

    @Override
    public void saveAboutMe(String content) {
        Options about = optionsRepository.findByOptionKey(SITE_ABOUTME);
        about.setOptionValue(content);
        optionsRepository.save(about);
    }

    @Override
    public String findAboutMe() {
        Options about = optionsRepository.findByOptionKey(SITE_ABOUTME);
        if (about == null) {
            return null;
        }
        return about.getOptionValue();
    }

    @Override
    public String findIcp() {
        Options icp = optionsRepository.findByOptionKey(SITE_ICP);
        if (icp == null) {
            return null;
        }
        return icp.getOptionValue();
    }

    @Override
    public void saveIcp(String icp) {
        Options dbIcp = optionsRepository.findByOptionKey(SITE_ICP);
        dbIcp.setOptionValue(icp);
        optionsRepository.save(dbIcp);
    }

    @Override
    public String findDescription() {
        Options desc = optionsRepository.findByOptionKey(SITE_DESCRIPTION);
        if (desc == null) {
            return null;
        }
        return desc.getOptionValue();
    }

    @Override
    public void saveDescription(String desc) {
        Options dbDesc = optionsRepository.findByOptionKey(SITE_DESCRIPTION);
        dbDesc.setOptionValue(desc);
        optionsRepository.save(dbDesc);
    }

    @Override
    public List<String> findBanner() {
        List<String> bannerList = new ArrayList<>();
        Options banner = optionsRepository.findByOptionKey(SITE_BANNER);
        if (banner == null) {
            return bannerList;
        }
        String value = banner.getOptionValue();
        if (StringUtils.isEmpty(value)) {
            return bannerList;
        }

        String[] split = value.split(",");
        for (String s : split) {
            bannerList.add(s);
        }
        return bannerList;
    }

    @Override
    public void saveBanner(String banner) {
        Options dbBanner = optionsRepository.findByOptionKey(SITE_BANNER);
        dbBanner.setOptionValue(banner);
        optionsRepository.save(dbBanner);
    }

    @Override
    public String findNotice() {
        Options notice = optionsRepository.findByOptionKey(SITE_NOTICE);
        if (notice == null) {
            return null;
        }
        return notice.getOptionValue();
    }

    @Override
    public void saveNotice(String notice) {
        Options dbNotice = optionsRepository.findByOptionKey(SITE_NOTICE);
        dbNotice.setOptionValue(notice);
        optionsRepository.save(dbNotice);
    }

    @Override
    public String findSiteName() {
        Options siteName = optionsRepository.findByOptionKey(SITE_NAME);
        if (siteName == null) {
            return null;
        }
        return siteName.getOptionValue();
    }

    @Override
    public void saveSiteName(String siteName) {
        Options dbName = optionsRepository.findByOptionKey(SITE_NAME);
        dbName.setOptionValue(siteName);
        optionsRepository.save(dbName);
    }
}
