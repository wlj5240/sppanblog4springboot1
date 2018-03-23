package net.sppan.blog.controller.front;

import net.sppan.blog.common.JsonResult;
import net.sppan.blog.entity.Tag;
import net.sppan.blog.service.TagService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * create by SPPan 2018/1/10
 */
@RestController
@RequestMapping("/ajax/tag")
public class TagController {

    @Resource
    private TagService tagService;

    @RequestMapping("list")
    public JsonResult listAll() {
        List<Tag> list = tagService.findAll();
        return JsonResult.ok().setData(list);
    }
}
