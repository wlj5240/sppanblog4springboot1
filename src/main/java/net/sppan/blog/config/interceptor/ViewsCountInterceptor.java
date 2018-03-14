package net.sppan.blog.config.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import net.sppan.blog.service.BlogService;

/**
 * @author SPPan
 * <p>
 * 公用拦截器
 */
@Component
public class ViewsCountInterceptor implements HandlerInterceptor {
    private static Map<String, Long> viewList = new HashMap<>();

    @Resource
    private BlogService blogService;

    @SuppressWarnings({"rawtypes"})
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Map map = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String blogId = (String) map.get("id");
        String sessionId = request.getSession().getId();
        String viewKey = sessionId + blogId;
        Long currentTimeMillis = System.currentTimeMillis();
        if (viewList.containsKey(viewKey)) {
            Long oldTimeMillis = (Long) viewList.get(viewKey);
            if (currentTimeMillis - oldTimeMillis > 3 * 60 * 1000) { // 3分钟
                viewList.put(viewKey, currentTimeMillis);
            }
        } else {
            blogService.updateViewsCountById(Long.valueOf(blogId));
            viewList.put(viewKey, currentTimeMillis);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

}
