package net.sppan.blog.controller.front;

import net.sppan.blog.common.JsonResult;
import net.sppan.blog.entity.Youlian;
import net.sppan.blog.service.YoulianService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ajax/youlian")
public class YoulianController extends _BaseController {
    @Resource
    private YoulianService youlianService;

    @RequestMapping("/list")
    public JsonResult listAll() {
        List<Youlian> list = youlianService.findAllVisiable();
        return JsonResult.ok().setData(list);
    }
}
