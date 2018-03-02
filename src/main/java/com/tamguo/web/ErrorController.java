package com.tamguo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorController {

	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public String error404(){
		return "404";
	}
	
	@RequestMapping(value = "/500", method = RequestMethod.GET)
	public String error500(){
		return "500";
	}
}
