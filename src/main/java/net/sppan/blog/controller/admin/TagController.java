package net.sppan.blog.controller.admin;

import net.sppan.blog.controller.BaseController;
import net.sppan.blog.entity.Tag;
import net.sppan.blog.service.TagService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * create by SPPan 2018/1/10
 *
 */
@Controller
@RequestMapping("/admin/tag")
public class TagController extends BaseController {
    @Resource
    private TagService tagService;

    @GetMapping("/index")
    public String index() {
        return "admin/tag/index";
    }

    @PostMapping("/list")
    @ResponseBody
    public Page<Tag> list() {
        PageRequest pageRequest = getPageRequest();
        Page<Tag> page = tagService.findAll(pageRequest);
        return page;
    }

    @GetMapping("/form")
    public String form(@RequestParam(required = false) Long id, ModelMap map) {
        if (id != null) {
            Tag tag = tagService.findById(id);
            map.put("tag", tag);
        }
        return "admin/tag/form";
    }
}
