package net.sppan.blog.controller.admin;

import net.sppan.blog.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * create by SPPan 2018/1/10
 *
 */
@Controller
@RequestMapping("/admin/about")
public class AboutController extends BaseController {

    @GetMapping("/index")
    public String index() {
        return "admin/about/index";
    }
}
