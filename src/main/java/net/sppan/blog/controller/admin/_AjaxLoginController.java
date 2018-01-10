package net.sppan.blog.controller.admin;

import net.sppan.blog.common.JsonResult;
import net.sppan.blog.controller.BaseController;
import net.sppan.blog.entity.Session;
import net.sppan.blog.service.UserService;
import net.sppan.blog.utils.CookieKit;
import net.sppan.blog.utils.IpKit;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/ajax")
public class _AjaxLoginController extends BaseController {

    @Resource
    private UserService userService;

    /**
     * 登录操作
     *
     * @param request
     * @param response
     * @param username  用户名
     * @param password  密码
     * @param keepLogin 保持登录
     * @return
     */
    @PostMapping("/login")
    public JsonResult login(
            HttpServletRequest request,
            HttpServletResponse response,
            String username,
            String password,
            @RequestParam(required = false) Boolean keepLogin
    ) {
        try {
            String ip = IpKit.getRealIp(request);
            //登录系统
            Session session = userService.login(username, password, keepLogin, ip);

            //把sessionID写入cookie
            CookieKit.setSessionId2Cookie(response, session.getSessionId(), ip, true);

            return JsonResult.ok().set("returnUrl", "/admin");
        } catch (Exception e) {
            return JsonResult.fail(e.getMessage());
        }
    }

    @PostMapping("/updatePwd")
    public JsonResult updatePwd(
            HttpServletRequest request,
            HttpServletResponse response,
            String oldpassword,
            String password1,
            String password2
    ) {
        try {
            userService.updatePassword(getLoginUser(), oldpassword, password1, password2);
            CookieKit.removeSessionIdFromCookie(response);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }
}
