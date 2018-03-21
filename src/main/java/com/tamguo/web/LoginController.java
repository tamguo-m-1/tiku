package com.tamguo.web;

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
public class LoginController {
	
	@Autowired
	private IMemberService iMemberService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView model){
		model.setViewName("login");
		model.addObject("isVerifyCode" , "0");
		return model;
	}
	
	@RequestMapping(value = "/submitLogin", method = RequestMethod.POST)
	public ModelAndView submitLogin(String  username , String password , String verifyCode , ModelAndView model , HttpSession session){
		Result result = iMemberService.login(username, password , verifyCode);
		if(result.getCode() == 200){
			session.setAttribute("currMember", result.getResult());
			model.setViewName("index");	
		}else{
			model.setViewName("login");	
			model.addObject("code", result.getCode());
			model.addObject("msg" , result.getMessage());
			model.addObject("isVerifyCode" , ((MemberEntity)result.getResult()).getLoginFailureCount() >=3 ? "1" : "0");
			model.addObject("username", username);
		}
		
		return model;
	}
	
	@RequestMapping(value = "/miniLogin", method = RequestMethod.GET)
	@ResponseBody
    public Result miniLogin(String username , String password , String captcha, ModelAndView model , HttpSession session) {
		Result result = iMemberService.login(username, password , captcha);
		if(result.getCode() == 200){
			session.setAttribute("currMember", result.getResult());
		}
		return result;
    }

}
