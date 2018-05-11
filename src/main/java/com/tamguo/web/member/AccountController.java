package com.tamguo.web.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tamguo.model.MemberEntity;
import com.tamguo.service.IMemberService;
import com.tamguo.util.Result;

@Controller
public class AccountController {
	
	@Autowired
	public IMemberService memberService;

	@RequestMapping(value = "/member/account", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model , HttpSession session){
		model.setViewName("member/account");
		MemberEntity member = (MemberEntity) session.getAttribute("currMember");
		model.addObject("member" , memberService.findByUid(member.getUid()));
		return model;
	}
	
	@RequestMapping(value = "/member/account/update", method = RequestMethod.POST)
	@ResponseBody
	public Result updateMember(MemberEntity member , HttpSession session){
		MemberEntity m = (MemberEntity) session.getAttribute("currMember");
		
		member.setUid(m.getUid());
		memberService.updateMember(member);
		return Result.successResult(member);
	}
	
	@RequestMapping(value = "/member/updatePwd", method = RequestMethod.GET)
	public ModelAndView updatePwd(ModelAndView model){
		model.setViewName("member/updatePwd");
		return model;
	}
}
