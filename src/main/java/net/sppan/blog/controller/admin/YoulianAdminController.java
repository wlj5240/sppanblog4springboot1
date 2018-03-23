package net.sppan.blog.controller.admin;

import net.sppan.blog.common.JsonResult;
import net.sppan.blog.entity.Youlian;
import net.sppan.blog.service.YoulianService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * create by SPPan 2018/1/10
 */
@RestController
@RequestMapping("/ajax/admin/youlian")
public class YoulianAdminController extends _BaseAdminController {
    private static Logger logger = LoggerFactory.getLogger(YoulianAdminController.class);
    @Resource
    private YoulianService youlianService;

    @PostMapping("/list")
    public JsonResult list() {
        try {
            PageRequest pageRequest = getPageRequest();
            Page<Youlian> page = youlianService.findAll(pageRequest);
            return JsonResult.ok().setData(page);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
    }

    @PostMapping("/save")
    public JsonResult save(Youlian youlian) {
        try {
            youlianService.saveOrUpdate(youlian);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    @PostMapping("/{id}/del")
    public JsonResult delete(@PathVariable Long id) {
        try {
            youlianService.delete(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    @PostMapping("/{id}/change_status")
    public JsonResult changeStatus(@PathVariable Long id) {
        try {
            youlianService.changeStatus(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }
}
