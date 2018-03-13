package net.sppan.blog.controller.admin;

import net.sppan.blog.common.JsonResult;
import net.sppan.blog.controller.BaseController;
import net.sppan.blog.entity.Youlian;
import net.sppan.blog.service.YoulianService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * create by SPPan 2018/1/10
 *
 */
@RestController
@RequestMapping("/ajax/admin/youlian")
public class YoulianController extends BaseController {
    @Resource
    private YoulianService youlianService;

    @PostMapping("/list")
    public JsonResult list() {
        PageRequest pageRequest = getPageRequest();
        Page<Youlian> page = youlianService.findAll(pageRequest);
        JsonResult ok = JsonResult.ok();
        ok.setData(page);
        return ok;
    }

    @PostMapping("/save")
    public JsonResult save(Youlian youlian) {
        try {
            youlianService.saveOrUpdate(youlian);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    @PostMapping("/{id}/del")
    public JsonResult delete(@PathVariable Long id) {
        try {
            youlianService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    @PostMapping("/{id}/changeStatus")
    public JsonResult changeStatus(@PathVariable Long id) {
        try {
            youlianService.changeStatus(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }
}
