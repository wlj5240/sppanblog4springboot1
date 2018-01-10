package net.sppan.blog.controller.admin;

import net.sppan.blog.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * create by SPPan 2018/1/10
 *
 */
@Controller
public class IndexController extends BaseController {

    @GetMapping("/admin")
    public String index() {
        return "admin/index";
    }

    @GetMapping("/admin/welcome")
    public String welcome() {
        return "admin/welcome";
    }
}
