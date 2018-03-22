package net.sppan.blog.controller.admin;

import net.sppan.blog.common.JsonResult;
import net.sppan.blog.controller.BaseController;
import net.sppan.blog.entity.Tag;
import net.sppan.blog.service.TagService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ajax/admin/tag")
public class _TagController extends BaseController {
    @Resource
    private TagService tagService;

    @PostMapping("/list")
    public JsonResult list() {
        PageRequest pageRequest = getPageRequest();
        Page<Tag> page = tagService.findAll(pageRequest);
        JsonResult ok = JsonResult.ok();
        ok.setData(page);
        return ok;
    }

    @PostMapping("/save")
    public JsonResult save(Tag tag) {
        try {
            tagService.saveOrUpdate(tag);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    @PostMapping("/{id}/del")
    public JsonResult delete(@PathVariable Long id) {
        try {
            tagService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    @PostMapping("/{id}/changeStatus")
    public JsonResult changeStatus(@PathVariable Long id) {
        try {
            tagService.changeStatus(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    @GetMapping("/tags_name")
    public List<String> tags_name() {
        List<String> tagsList = tagService.findAllNameList();
        return tagsList;
    }

}
