package net.sppan.blog.controller.admin;

import net.sppan.blog.common.JsonResult;
import net.sppan.blog.common.param.SettingsParam;
import net.sppan.blog.service.OptionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/ajax/admin/options")
public class OptionsAdminController extends _BaseAdminController {

    private static Logger logger = LoggerFactory.getLogger(OptionsAdminController.class);

    @Resource
    private OptionsService optionsService;

    @PostMapping("about/content")
    public JsonResult content() {
        try {
            String aboutMe = optionsService.findAboutMe();
            return JsonResult.ok().setData(aboutMe);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
    }

    @PostMapping("about/save")
    public JsonResult save(String content) {
        try {
            optionsService.saveAboutMe(content);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    @PostMapping("settings")
    public JsonResult settings() {
        try {
            String siteName = optionsService.findSiteName();
            String icp = optionsService.findIcp();
            List<String> banner = optionsService.findBanner();
            String description = optionsService.findDescription();
            String notice = optionsService.findNotice();
            HashMap<String, Object> data = new HashMap<>();
            data.put(OptionsService.SITE_NAME, siteName);
            data.put(OptionsService.SITE_ICP, icp);
            data.put(OptionsService.SITE_BANNER, StringUtils.collectionToDelimitedString(banner, ","));
            data.put(OptionsService.SITE_DESCRIPTION, description);
            data.put(OptionsService.SITE_NOTICE, notice);
            return JsonResult.ok().setData(data);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
    }

    @PostMapping("settings/save")
    public JsonResult saveSettings(SettingsParam param) {
        try {
            optionsService.saveSiteName(param.getSiteName());
            optionsService.saveIcp(param.getSiteIcp());
            optionsService.saveBanner(param.getSiteBanner());
            optionsService.saveDescription(param.getSiteDescription());
            optionsService.saveNotice(param.getSiteNotice());
            return JsonResult.ok();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
    }
}
