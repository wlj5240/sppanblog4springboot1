package net.sppan.blog.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sppan.blog.common.Constat;
import net.sppan.blog.entity.User;
import net.sppan.blog.utils.CacheKit;
import net.sppan.blog.utils.StrKit;

import org.springframework.data.domain.PageRequest;

/**
 * create by SPPan 2018/1/10
 */
public class BaseAdminController {
    @Resource
    private HttpServletRequest request;

    protected User getLoginUser() {
        Object loginUser = request.getAttribute("loginUser");
        if (loginUser != null) {
            return (User) loginUser;
        }
        return null;
    }

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
