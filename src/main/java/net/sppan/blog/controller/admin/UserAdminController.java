package net.sppan.blog.controller.admin;

import net.sppan.blog.common.JsonResult;
import net.sppan.blog.controller.BaseController;
import net.sppan.blog.entity.User;
import net.sppan.blog.service.UserService;
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
@RequestMapping("/ajax/admin/user")
public class UserAdminController extends BaseController {
    @Resource
    private UserService userService;

    @PostMapping("/list")
    public JsonResult list() {
        PageRequest pageRequest = getPageRequest();
        Page<User> page = userService.findAll(pageRequest);
        JsonResult ok = JsonResult.ok();
        ok.setData(page);
        return ok;
    }

    @PostMapping("/save")
    public JsonResult save(User user) {
        try {
            userService.saveOrUpdate(user);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    @PostMapping("/{id}/del")
    public JsonResult delete(@PathVariable Long id) {
        try {
            userService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }
}
