package net.sppan.blog.controller.front;

import net.sppan.blog.common.JsonResult;
import net.sppan.blog.entity.Blog;
import net.sppan.blog.lucene.SearcherKit;
import net.sppan.blog.service.BlogService;
import net.sppan.blog.utils.StrKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * create by SPPan 2018/1/10
 */
@RestController
@RequestMapping("/ajax/post")
public class BlogController extends _BaseController {
    private static Logger logger = LoggerFactory.getLogger(_BaseController.class);
    @Resource
    private BlogService blogService;
    @Resource
    private SearcherKit searcherKit;

    @RequestMapping("/list")
    public JsonResult listAll() {
        try {
            Page<Blog> page = blogService.findAll(getPageRequest());
            return JsonResult.ok().setData(page);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
    }

    @RequestMapping("/search")
    public JsonResult index(
            @RequestParam String keyword,
            @RequestParam(name = "pageNumber", required = false, defaultValue = "1") Integer pageNumber) {
        JsonResult ok = JsonResult.ok();
        if (StrKit.isBlank(keyword)) {
            return ok;
        }
        try {
            Page<Blog> page = searcherKit.search(pageNumber, 10, keyword);
            ok.setData(page);
            return ok;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
    }

    @RequestMapping("/category/{categoryId}")
    public JsonResult listBycategoryId(@PathVariable("categoryId") Long categoryId) {
        try {
            Page<Blog> page = blogService.findByCategoryANDPrivacy(categoryId, 0, getPageRequest());
            return JsonResult.ok().setData(page);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
    }

    @RequestMapping("/tag/{tagName}")
    public JsonResult listByTagId(
            @PathVariable("tagName") String tagName) {
        try {
            Page<Blog> page = blogService.findByTagName(tagName, getPageRequest());
            return JsonResult.ok().setData(page);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
    }

    @RequestMapping("/view/{id}")
    public JsonResult view(@PathVariable("id") Long id) {
        try {
            Blog blog = blogService.findById(id);
            return JsonResult.ok().setData(blog);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
    }
}
