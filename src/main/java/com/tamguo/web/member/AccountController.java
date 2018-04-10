package com.tamguo.web.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {

	@RequestMapping(value = "/member/account", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model){
		model.setViewName("member/account");
		return model;
	}
	
	
	@RequestMapping(value = "/member/updatePwd", method = RequestMethod.GET)
	public ModelAndView updatePwd(ModelAndView model){
		model.setViewName("member/updatePwd");
		return model;
	}
}
