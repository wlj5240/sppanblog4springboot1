package net.sppan.blog.controller.admin;

import net.sppan.blog.common.JsonResult;
import net.sppan.blog.controller.BaseController;
import net.sppan.blog.service.OptionsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/ajax/admin/about")
public class _AjaxAboutController extends BaseController {

    @Resource
    private OptionsService optionsService;

    @PostMapping("/save")
    public JsonResult save(String content) {
        try {
            optionsService.saveAboutMe(content);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();

    }
}
