package net.sppan.blog.controller.admin;

import net.sppan.blog.common.JsonResult;
import net.sppan.blog.controller.BaseController;
import net.sppan.blog.entity.Blog;
import net.sppan.blog.service.BlogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/ajax/admin/blog")
public class BlogAdminController extends BaseController {

    @Resource
    private BlogService blogService;

    @PostMapping("/list")
    public JsonResult list() {
        PageRequest pageRequest = getPageRequest();
        Page<Blog> page = blogService.findAll(pageRequest);
        JsonResult ok = JsonResult.ok();
        ok.setData(page);
        return ok;
    }

    @PostMapping("/save")
    public JsonResult save(Blog blog) {
        try {
            blog.setAuthor(getLoginUser());
            blogService.saveOrUpdate(blog);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    @PostMapping("/{id}/change")
    public JsonResult change(@PathVariable Long id, String type) {
        try {
            blogService.change(id, type);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    @PostMapping("/{id}/del")
    public JsonResult delete(@PathVariable Long id) {
        try {
            blogService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }
}
