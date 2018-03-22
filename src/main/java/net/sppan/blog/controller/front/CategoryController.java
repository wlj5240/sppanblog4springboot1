package net.sppan.blog.controller.front;

import net.sppan.blog.common.JsonResult;
import net.sppan.blog.entity.Category;
import net.sppan.blog.service.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController("frontCategoryController")
@RequestMapping("/c")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @RequestMapping("/list")
    public JsonResult listAll() {
        List<Category> list = categoryService.findVisible();
        return JsonResult.ok().setData(list);
    }
}
