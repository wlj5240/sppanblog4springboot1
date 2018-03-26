package net.sppan.blog.controller.front;

import net.sppan.blog.common.JsonResult;
import net.sppan.blog.service.OptionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ajax/options")
public class OptionsController extends _BaseController {
    private static Logger logger = LoggerFactory.getLogger(OptionsController.class);
    @Autowired
    private OptionsService optionsService;

    @RequestMapping("/icp")
    public JsonResult icp() {
        try {
            String icp = optionsService.findIcp();
            return JsonResult.ok().setData(icp);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
    }

    @RequestMapping("/about")
    public JsonResult about(){
        try {
            String about = optionsService.findAboutMe();
            return JsonResult.ok().setData(about);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
    }

    @RequestMapping("/description")
    public JsonResult description(){
        try {
            String desc = optionsService.findDescription();
            return JsonResult.ok().setData(desc);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
    }

    @RequestMapping("/banner")
    public JsonResult banner(){
        try {
            List<String> banner = optionsService.findBanner();
            return JsonResult.ok().setData(banner);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
    }

    @RequestMapping("/notice")
    public JsonResult notice(){
        try {
            String notice = optionsService.findNotice();
            return JsonResult.ok().setData(notice);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
    }
}
