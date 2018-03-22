package net.sppan.blog.controller.front;

import net.sppan.blog.common.JsonResult;
import net.sppan.blog.entity.Blog;
import net.sppan.blog.entity.Tag;
import net.sppan.blog.service.BlogService;
import net.sppan.blog.service.TagService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * create by SPPan 2018/1/10
 */
@RestController("frontTagController")
public class TagController {

    @Resource
    private BlogService blogService;
    @Resource
    private TagService tagService;

    @RequestMapping("/t/{tagName}")
    public JsonResult index(
            @PathVariable String tagName,
            @RequestParam(required = false, defaultValue = "1") Integer p) {
        PageRequest pageRequest = new PageRequest(p - 1, 5);
        Page<Blog> page = blogService.findByTagName(tagName, pageRequest);
        JsonResult ok = JsonResult.ok();
        ok.setData(page);
        return ok;
    }

    @RequestMapping("list")
    public JsonResult listAll() {
        List<Tag> list = tagService.findAll();
        return JsonResult.ok().setData(list);
    }
}
