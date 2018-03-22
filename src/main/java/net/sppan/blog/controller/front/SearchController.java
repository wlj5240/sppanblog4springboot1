package net.sppan.blog.controller.front;

import net.sppan.blog.common.JsonResult;
import net.sppan.blog.entity.Blog;
import net.sppan.blog.lucene.SearcherKit;
import net.sppan.blog.utils.StrKit;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * create by SPPan 2018/1/10
 */
@RestController("frontSearchController")
public class SearchController {

    @Resource
    private SearcherKit searcherKit;

    @RequestMapping("/s")
    public JsonResult index(
            @RequestParam String keyword,
            @RequestParam(required = false, defaultValue = "1") Integer p) {
        JsonResult ok = JsonResult.ok();
        if (StrKit.isBlank(keyword)) {
            return ok;
        }
        Page<Blog> page = searcherKit.search(p, 10, keyword);
        ok.setData(page);
        return ok;
    }
}
