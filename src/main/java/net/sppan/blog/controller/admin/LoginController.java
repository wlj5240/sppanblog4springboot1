package net.sppan.blog.controller.admin;

import net.sppan.blog.controller.BaseController;
import net.sppan.blog.service.UserService;
import net.sppan.blog.utils.CookieKit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * create by SPPan 2018/1/10
 *
 */
@Controller
public class LoginController extends BaseController {

    @Resource
    private UserService userService;

    /**
     * 跳转登录页面
     *
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    /**
     * 注销用户
     *
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        String sessionId = CookieKit.getSessionIdFromCookie(request, response);
        userService.logout(sessionId);
        CookieKit.removeSessionIdFromCookie(response);
        return "redirect:/admin";
    }

    @GetMapping("/update_form")
    public String updatePWD() {
        return "admin/update_form";
    }

}
