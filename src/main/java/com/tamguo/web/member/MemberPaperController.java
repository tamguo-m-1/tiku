package com.tamguo.web.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberPaperController {

	@RequestMapping(value = "/member/paperList", method = RequestMethod.GET)
	public ModelAndView paperList(ModelAndView model){
		model.setViewName("member/paperList");
		return model;
	}
	
}
