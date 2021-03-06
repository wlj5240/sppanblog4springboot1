package net.sppan.blog.controller.admin;

import net.sppan.blog.common.JsonResult;
import net.sppan.blog.common.param.LoginParam;
import net.sppan.blog.entity.Session;
import net.sppan.blog.entity.User;
import net.sppan.blog.service.SessionService;
import net.sppan.blog.service.UserService;
import net.sppan.blog.utils.IpKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * 登录相关controller
 * <p>
 * create by SPPan
 */
@RestController
@RequestMapping("/ajax/admin/login")
public class LoginAdminController extends _BaseAdminController {

    private static Logger logger = LoggerFactory.getLogger(LoginAdminController.class);

    @Resource
    private UserService userService;
    @Autowired
    private SessionService sessionService;

    /**
     * 登录操作
     *
     * @param request
     * @param response
     * @param loginParam 登录信息
     * @return
     */
    @PostMapping("/login")
    public JsonResult login(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginParam loginParam) {
        try {
            String ip = IpKit.getRealIp(request);
            //登录系统
            Session session = userService.login(loginParam.getUsername(), loginParam.getPassword(), loginParam.getKeepLogin(), ip);
            //暂时把sessionId当成token给前台
            HashMap<String, Object> data = new HashMap<>();
            data.put("token", session.getSessionId());
            return JsonResult.ok().setData(data);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
    }

    @GetMapping("/user-info")
    public JsonResult getUserInfo(String token) {
        try {
            Session session = sessionService.findBySessionId(token);
            User user = session.getUser();
            HashMap<String, Object> data = new HashMap<>();
            data.put("name", user.getUserName());
            data.put("avatar", user.getAvatar());
            return JsonResult.ok().setData(data);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
    }

    @PostMapping("/updatePwd")
    public JsonResult updatePwd(HttpServletResponse response, String oldpassword, String password1, String password2) {
        try {
            userService.updatePassword(getLoginUser(), oldpassword, password1, password2);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    /**
     * 注销用户
     *
     * @param token
     * @return
     */
    @GetMapping("/logout")
    public JsonResult logout(String token) {
        try {
            userService.logout(token);
            return JsonResult.ok();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
    }
}
