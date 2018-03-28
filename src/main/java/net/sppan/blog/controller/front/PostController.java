package net.sppan.blog.controller.front;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import net.sppan.blog.common.JsonResult;
import net.sppan.blog.common.vo.PostVo;
import net.sppan.blog.entity.Post;
import net.sppan.blog.lucene.SearcherKit;
import net.sppan.blog.service.PostService;
import net.sppan.blog.utils.StrKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * create by SPPan 2018/1/10
 */
@RestController
@RequestMapping("/ajax/post")
public class PostController extends _BaseController {
    private static Logger logger = LoggerFactory.getLogger(_BaseController.class);
    @Resource
    private PostService postService;
    @Resource
    private SearcherKit searcherKit;

    // 一分钟内不允许重复点赞
    private static Cache<String, Integer> upVoteCache = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .build();
    // 一分钟内不允许重复点踩
    private static Cache<String, Integer> downVoteCache = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .build();

    @RequestMapping("/list")
    public JsonResult listAll() {
        try {
            Page<Post> page = postService.findAll(getPageRequest());
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
            Page<Post> page = searcherKit.search(pageNumber, 10, keyword);
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
            Page<Post> page = postService.findByCategoryANDPrivacy(categoryId, 0, getPageRequest());
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
            Page<Post> page = postService.findByTagName(tagName, getPageRequest());
            return JsonResult.ok().setData(page);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
    }

    @RequestMapping("/view/{id}")
    public JsonResult view(@PathVariable("id") Long id) {
        try {
            PostVo vo = postService.findVoById(id);
            return JsonResult.ok().setData(vo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
    }

    @RequestMapping("/hots/{n}")
    public JsonResult hotsN(@PathVariable Integer n) {
        try {
            List<Post> list = postService.findHotN(n);
            return JsonResult.ok().setData(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
    }

    @RequestMapping("/featureds/{n}")
    public JsonResult featuredsN(@PathVariable Integer n) {
        try {
            List<Post> list = postService.findFeaturedN(n);
            return JsonResult.ok().setData(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
    }

    @RequestMapping("/up_vote/{id}")
    public JsonResult upVote(HttpServletRequest request, @PathVariable Long id) {
        try {
            String key = request.getSession().getId() + id;
            Integer ifPresent = upVoteCache.getIfPresent(key);
            upVoteCache.put(key, 1);
            if (ifPresent == null) {
                postService.increaseUpVote(id);
                return JsonResult.ok().setData(0);
            }
            return JsonResult.ok().setData(-1);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
    }

    @RequestMapping("/down_vote/{id}")
    public JsonResult downVote(HttpServletRequest request, @PathVariable Long id) {
        try {
            String key = request.getSession().getId() + id;
            Integer ifPresent = downVoteCache.getIfPresent(key);
            downVoteCache.put(key, 1);
            if (ifPresent == null) {
                postService.increaseDownVote(id);
                return JsonResult.ok().setData(0);
            }
            return JsonResult.ok().setData(-1);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.fail(e.getMessage());
        }
    }
}
