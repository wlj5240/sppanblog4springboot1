package net.sppan.blog.controller.admin;

import net.sppan.blog.controller.BaseController;
import net.sppan.blog.entity.User;
import net.sppan.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * create by SPPan 2018/1/10
 *
 */
@Controller
@RequestMapping("/admin/user")
public class UserController extends BaseController {
    @Resource
    private UserService userService;

    @GetMapping("/index")
    public String index() {
        return "admin/user/index";
    }

    @GetMapping("/form")
    public String form(@RequestParam(required = false) Long id, ModelMap map) {
        if (id != null) {
            User user = userService.findById(id);
            map.put("user", user);
        }
        return "admin/user/form";
    }
}
