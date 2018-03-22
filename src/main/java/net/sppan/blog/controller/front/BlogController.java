package net.sppan.blog.controller.front;

import net.sppan.blog.common.JsonResult;
import net.sppan.blog.entity.Blog;
import net.sppan.blog.service.BlogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * create by SPPan 2018/1/10
 */
@RestController("frontBlogController")
@RequestMapping("/b")
public class BlogController {

    @Resource
    private BlogService blogService;

    @RequestMapping("/{categoryId}")
    public JsonResult list(
            @PathVariable("categoryId") Long categoryId,
            @RequestParam(required = false, defaultValue = "1") Integer p) {
        PageRequest pageRequest = new PageRequest(p - 1, 5);
        Page<Blog> page = blogService.findByCategoryANDPrivacy(categoryId, 0, pageRequest);
        JsonResult ok = JsonResult.ok();
        ok.setData(page);
        return ok;
    }

    @RequestMapping("/view/{id}")
    public JsonResult view(
            @PathVariable("id") Long id) {
        Blog blog = blogService.findById(id);
        JsonResult ok = JsonResult.ok();
        ok.setData(blog);
        return ok;
    }


}
