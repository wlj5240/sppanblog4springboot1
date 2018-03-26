package net.sppan.blog.controller.admin;

import net.sppan.blog.common.JsonResult;
import net.sppan.blog.entity.Post;
import net.sppan.blog.lucene.SearcherKit;
import net.sppan.blog.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/ajax/admin/post")
public class PostAdminController extends _BaseAdminController {
    private static Logger logger = LoggerFactory.getLogger(PostAdminController.class);

    @Resource
    private PostService postService;
    @Resource
    private SearcherKit searcherKit;

    @PostMapping("/list")
    public JsonResult list() {
        try {
            PageRequest pageRequest = getPageRequest();
            Page<Post> page = postService.findAll(pageRequest);
            return JsonResult.ok().setData(page);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
    }

    @PostMapping("/save")
    public JsonResult save(Post post) {
        try {
            post.setAuthor(getLoginUser());
            postService.saveOrUpdate(post);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    @PostMapping("/{id}/change")
    public JsonResult change(@PathVariable Long id, String type) {
        try {
            postService.change(id, type);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    @PostMapping("/{id}/change_status")
    public JsonResult changeStatus(@PathVariable Long id) {
        try {
            postService.change(id, "status");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    @PostMapping("/{id}/del")
    public JsonResult delete(@PathVariable Long id) {
        try {
            postService.delete(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    @PostMapping("/reloadIndex")
    public JsonResult reloadIndex() {
        try {
            searcherKit.reloadIndex(postService.findAll());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }
}
