package com.tamguo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tamguo.service.IMemberService;
import com.tamguo.util.Result;

@Controller
public class PasswordController {
	
	@Autowired
	private IMemberService iMemberService;
	
	@RequestMapping(value = "password/confirmAccount", method = RequestMethod.GET)
	public ModelAndView confirmAccount(ModelAndView model){
		model.setViewName("password/confirmAccount");
		return model;
	}
	
	@RequestMapping(value = "password/confirmAccount", method = RequestMethod.POST)
	public ModelAndView submitConfirmAccount(String username , String veritycode , ModelAndView model){
		Result result = iMemberService.confirmAccount(username, veritycode);
		if(result.getCode() == 200){
			model.setViewName("password/securityCheck");
			model.addObject("result", result);
		}else{
			model.setViewName("password/confirmAccount");
			model.addObject("account", username);
			model.addObject("veritycode", veritycode);
			model.addObject("code", result.getCode());
		}
		return model;
	}
	
	@RequestMapping(value = "password/securityCheck", method = RequestMethod.POST)
	public ModelAndView securityCheck(String username , String vcode , ModelAndView model){
		Result result = iMemberService.securityCheck(username , vcode);
		if(result.getCode() == 200){
			model.addObject("username", username);
			model.addObject("resetPasswordKey" , result.getResult());
			model.setViewName("password/resetPassword");
		}else{
			model.setViewName("password/securityCheck");
		}
		return model;
	}
	
	@RequestMapping(value = "password/resetPassword", method = RequestMethod.POST)
	public ModelAndView resetPassword(String resetPasswordKey , String username , String password , String verifypwd , ModelAndView model){
		Result result = iMemberService.resetPassword(resetPasswordKey , username , password , verifypwd);
		if(result.getCode() == 200){
			model.setViewName("/");
		}else{
			model.setViewName("password/resetPassword");
		}
		return model;
	}
	
	@RequestMapping(value = "password/checkAccount", method = RequestMethod.GET)
	@ResponseBody
	public Result checkAccount(String account){
		return iMemberService.checkAccount(account);
	}
	
}
