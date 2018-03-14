package net.sppan.blog.controller.admin;

import net.sppan.blog.common.JsonResult;
import net.sppan.blog.controller.BaseController;
import net.sppan.blog.service.OptionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/ajax/admin/about")
public class AboutController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(AboutController.class);

    @Resource
    private OptionsService optionsService;

    @PostMapping("content")
    public JsonResult content() {
        try {
            String aboutMe = optionsService.getAboutMe();
            return JsonResult.ok().setData(aboutMe);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
    }

    @PostMapping("/save")
    public JsonResult save(String content) {
        try {
            optionsService.saveAboutMe(content);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();

    }
}
