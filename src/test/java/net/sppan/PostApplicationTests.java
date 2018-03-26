package net.sppan;

import net.sppan.blog.BlogApplication;
import net.sppan.blog.entity.Post;
import net.sppan.blog.service.PostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogApplication.class)
public class PostApplicationTests {

    @Resource
    private PostService postService;

    @Test
    public void contextLoads() {
        List<Post> list = postService.findHotN(5);
        System.out.println(list);
    }

}
