package net.sppan;

import net.sppan.blog.BlogApplication;
import net.sppan.blog.entity.Blog;
import net.sppan.blog.service.BlogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogApplication.class)
public class BlogApplicationTests {

    @Resource
    private BlogService blogService;

    @Test
    public void contextLoads() {
        List<Blog> list = blogService.findHotN(5);
        System.out.println(list);
    }

}
