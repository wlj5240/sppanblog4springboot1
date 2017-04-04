package net.sppan.blog.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sppan.blog.controller.BaseController;

@Controller
public class IndexController extends BaseController {

	@RequestMapping("/")
	public String index(){
		return "redirect:/b/1";
	}
	
	@RequestMapping("/about")
	public String about(){
		return "front/about";
	}
}
