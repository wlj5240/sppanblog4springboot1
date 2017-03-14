package net.sppan.config;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import net.sppan.directive.BlogDirective;
import net.sppan.directive.CategoryDirective;
import net.sppan.directive.TagDirective;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FreeMarkerConfig {

    @Autowired
    private freemarker.template.Configuration configuration;
    
    @Resource
    private CategoryDirective categoryDirective;
    @Resource
    private BlogDirective blogDirective;
    @Resource
    private TagDirective tagDirective;

    @PostConstruct
    public void setSharedVariable() {
    	try {
			configuration.setSharedVariable("categoryList", categoryDirective);
			configuration.setSharedVariable("blogList", blogDirective);
			configuration.setSharedVariable("tagList", tagDirective);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
