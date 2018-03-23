package net.sppan.blog.controller.admin;

import net.sppan.blog.common.JsonResult;
import net.sppan.blog.entity.User;
import net.sppan.blog.service.UserService;
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
@RequestMapping("/ajax/admin/user")
public class UserAdminController extends _BaseAdminController {
    private static Logger logger = LoggerFactory.getLogger(UserAdminController.class);
    @Resource
    private UserService userService;

    @PostMapping("/list")
    public JsonResult list() {
        try {
            PageRequest pageRequest = getPageRequest();
            Page<User> page = userService.findAll(pageRequest);
            return JsonResult.ok().setData(page);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }

    }

    @PostMapping("/save")
    public JsonResult save(User user) {
        try {
            userService.saveOrUpdate(user);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    @PostMapping("/{id}/del")
    public JsonResult delete(@PathVariable Long id) {
        try {
            userService.delete(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }
}
