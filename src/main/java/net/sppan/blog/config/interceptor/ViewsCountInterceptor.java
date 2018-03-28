package net.sppan.blog.config.interceptor;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import net.sppan.blog.service.PostService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author SPPan
 * <p>
 * 公用拦截器
 */
@Component
public class ViewsCountInterceptor implements HandlerInterceptor {

    @Resource
    private PostService postService;
    private static Cache<String, Integer> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .build();

    @SuppressWarnings({"rawtypes"})
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Map map = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String blogId = (String) map.get("id");
        String sessionId = request.getSession().getId();
        String viewKey = sessionId + blogId;

        Integer ifPresent = cache.getIfPresent(viewKey);
        if (ifPresent == null) {
            postService.increaseViewsCountById(Long.valueOf(blogId));
        }
        cache.put(viewKey, 1);
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
