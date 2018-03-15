package com.tamguo.web.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {

	@RequestMapping(value = "/member/index", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model){
		model.setViewName("member/index");
		return model;
	}
}
