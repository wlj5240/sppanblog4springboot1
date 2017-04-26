package net.sppan.blog.controller.admin;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sppan.blog.common.JsonResult;
import net.sppan.blog.controller.BaseController;
import net.sppan.blog.entity.Youlian;
import net.sppan.blog.service.YoulianService;

@Controller
@RequestMapping("/admin/youlian")
public class AdminYoulianController extends BaseController {
	@Resource
	private YoulianService youlianService;

	@GetMapping("/index")
	public String index() {
		return "admin/youlian/index";
	}

	@PostMapping("/list")
	@ResponseBody
	public Page<Youlian> list() {
		PageRequest pageRequest = getPageRequest();
		Page<Youlian> page = youlianService.findAll(pageRequest);
		return page;
	}

	@GetMapping("/form")
	public String form(@RequestParam(required=false) Long id,
			ModelMap map
			){
		if(id != null){
			Youlian youlian = youlianService.findById(id);
			map.put("youlian", youlian);
		}
		return "admin/youlian/form";
	}
	
	@PostMapping("/save")
	@ResponseBody
	public JsonResult save(Youlian youlian){
		try {
			youlianService.saveOrUpdate(youlian);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.fail(e.getMessage());
		}
		return JsonResult.ok();
	}
	
	@PostMapping("/{id}/del")
	@ResponseBody
	public JsonResult delete(
			@PathVariable Long id
			){
		try {
			youlianService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.fail(e.getMessage());
		}
		return JsonResult.ok();
	}
	
	@PostMapping("/{id}/changeStatus")
	@ResponseBody
	public JsonResult changeStatus(@PathVariable Long id){
		try {
			youlianService.changeStatus(id);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.fail(e.getMessage());
		}
		return JsonResult.ok();
	}
}
