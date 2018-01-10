package net.sppan.blog.controller.admin;

import net.sppan.blog.controller.BaseController;
import net.sppan.blog.entity.Blog;
import net.sppan.blog.entity.Category;
import net.sppan.blog.service.BlogService;
import net.sppan.blog.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * create by SPPan 2018/1/10
 *
 */
@Controller
@RequestMapping("/admin/blog")
public class BlogController extends BaseController {

    @Resource
    private BlogService blogService;

    @Resource
    private CategoryService categoryService;

    @GetMapping("/index")
    public String index() {
        return "admin/blog/index";
    }

    @GetMapping("/form")
    public String form(@RequestParam(required = false) Long id, ModelMap map) {
        List<Category> categories = categoryService.findVisible();
        map.put("categories", categories);

        if (id != null) {
            Blog blog = blogService.findById(id);
            map.put("blog", blog);
        }
        return "admin/blog/form";
    }
}
