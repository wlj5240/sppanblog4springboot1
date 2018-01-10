package net.sppan.blog.controller.admin;

import net.sppan.blog.controller.BaseController;
import net.sppan.blog.entity.Category;
import net.sppan.blog.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * create by SPPan 2018/1/10
 *
 */
@Controller
@RequestMapping("/admin/category")
public class CategoryController extends BaseController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("/index")
    public String index() {
        return "admin/category/index";
    }

    @GetMapping("/form")
    public String form(@RequestParam(required = false) Long id, ModelMap map) {
        if (id != null) {
            Category category = categoryService.findById(id);
            map.put("category", category);
        }
        return "admin/category/form";
    }
}
