package net.sppan.blog.controller.front;

import net.sppan.blog.utils.StrKit;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * create by SPPan 2018/1/10
 */
public class BaseController {
    @Resource
    private HttpServletRequest request;

    protected PageRequest getPageRequest() {
        Integer pageNumber = 0;
        String pageNumberStr = request.getParameter("pageNumber");
        if (!StrKit.isBlank(pageNumberStr)) {
            pageNumber = Integer.parseInt(pageNumberStr) - 1;
        }

        Integer pageSize = 10;
        String pageSizeStr = request.getParameter("pageSize");
        if (!StrKit.isBlank(pageSizeStr)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        return new PageRequest(pageNumber, pageSize);
    }
}
