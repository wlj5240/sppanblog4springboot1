package net.sppan.blog.controller.admin;

import net.sppan.blog.common.JsonResult;
import net.sppan.blog.entity.Tag;
import net.sppan.blog.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ajax/admin/tag")
public class TagAdminAdminController extends BaseAdminController {
    private static Logger logger = LoggerFactory.getLogger(TagAdminAdminController.class);
    @Resource
    private TagService tagService;

    @PostMapping("/list")
    public JsonResult list() {
        try {
            PageRequest pageRequest = getPageRequest();
            Page<Tag> page = tagService.findAll(pageRequest);
            return JsonResult.ok().setData(page);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
    }

    @PostMapping("/save")
    public JsonResult save(Tag tag) {
        try {
            tagService.saveOrUpdate(tag);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    @PostMapping("/{id}/del")
    public JsonResult delete(@PathVariable Long id) {
        try {
            tagService.delete(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    @PostMapping("/{id}/changeStatus")
    public JsonResult changeStatus(@PathVariable Long id) {
        try {
            tagService.changeStatus(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    @GetMapping("/tags_name")
    public JsonResult tags_name() {
        try {
            List<String> tagsList = tagService.findAllNameList();
            return JsonResult.ok().setData(tagsList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
    }

}
