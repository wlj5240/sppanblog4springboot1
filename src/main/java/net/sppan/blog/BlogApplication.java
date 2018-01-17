package net.sppan.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * create by SPPan 2018/1/10
 */
@SpringBootApplication
public class BlogApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BlogApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}