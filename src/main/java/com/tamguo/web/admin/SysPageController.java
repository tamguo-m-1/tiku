package com.tamguo.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面视图
 */
@Controller
public class SysPageController {
	
	@RequestMapping("/admin/sys/{url}.html")
	public String page(@PathVariable("url") String url){
		return "admin/sys/"+url;
	}
	
	@RequestMapping("/admin/video/{url}.html")
	public String videoPage(@PathVariable("url") String url){
		return "admin/video/"+url;
	}
	
	@RequestMapping("/admin/member/{url}.html")
	public String memberPage(@PathVariable("url") String url){
		return "admin/member/"+url;
	}
}
