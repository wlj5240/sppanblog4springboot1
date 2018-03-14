package net.sppan.blog.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import net.sppan.blog.entity.Youlian;
import net.sppan.blog.service.YoulianService;

@Component
public class YoulianDirective implements TemplateDirectiveModel {

    @Resource
    private YoulianService youlianService;

    @Override
    public void execute(Environment environment, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
                        TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {

        List<Youlian> list = youlianService.findAllVisiable();
        environment.setVariable("list", new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25).build().wrap(list));
        if (templateDirectiveBody != null) {
            templateDirectiveBody.render(environment.getOut());
        }

    }

}
