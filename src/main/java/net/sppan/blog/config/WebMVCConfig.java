package net.sppan.blog.config;

import net.sppan.blog.config.interceptor.CommonInterceptor;
import net.sppan.blog.config.interceptor.LoginInterceptor;
import net.sppan.blog.config.interceptor.ViewsCountInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

/**
 * create by SPPan 2018/1/10
 */
@Configuration
public class WebMVCConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private CommonInterceptor commonInterceptor;
    @Resource
    private LoginInterceptor loginInterceptor;
    @Resource
    private ViewsCountInterceptor viewsCountInterceptor;

    /**
     * add interceptors
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(commonInterceptor).addPathPatterns("/**");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/ajax/admin/**");
        registry.addInterceptor(viewsCountInterceptor).addPathPatterns("/ajax/post/view/**");
    }
}
