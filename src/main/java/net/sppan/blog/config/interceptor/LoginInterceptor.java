package net.sppan.blog.config.interceptor;

import com.fasterxml.jackson.databind.util.JSONPObject;
import net.sppan.blog.common.Constat;
import net.sppan.blog.common.JsonResult;
import net.sppan.blog.entity.Session;
import net.sppan.blog.entity.User;
import net.sppan.blog.service.SessionService;
import net.sppan.blog.service.UserService;
import net.sppan.blog.utils.CacheKit;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author SPPan
 * <p>
 * 登录检查拦截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private CacheKit cacheKit;
    @Resource
    private SessionService sessionService;
    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String sessionId = request.getHeader("X-Token");
        //如果获取的session不为空，证明浏览器端有登录记录
        if (sessionId != null) {
            //从登录缓存中取当前的登录用户
            Object object = cacheKit.get(Constat.CACHE_LOGINUSER, sessionId);
            if (object != null) {
                request.setAttribute("loginUser", object);
                return true;
            } else {
                //如果缓存中没有登录的用户，则去数据库中取对应的session
                Session session = sessionService.findBySessionId(sessionId);
                //数据库中存在session，并且还没有过期，则进行登录操作
                if (session != null && session.getExpireAt() - System.currentTimeMillis() > 0) {
                    User user = session.getUser();
                    cacheKit.put(Constat.CACHE_LOGINUSER, sessionId, user);
                    request.setAttribute("loginUser", user);
                    return true;
                }
            }
        }
        //跳转到登录页面
        response.getWriter().write("{\"code\"=80233}");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}
