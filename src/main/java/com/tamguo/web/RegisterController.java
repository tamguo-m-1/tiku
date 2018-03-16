package com.tamguo.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tamguo.service.IMemberService;
import com.tamguo.util.Result;

@Controller
public class RegisterController {
	
	@Autowired
	private IMemberService iMemberService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(ModelAndView model , HttpSession session) {
		model.setViewName("register");
		return model;
    }
	
	@RequestMapping(value = "/checkUsername", method = RequestMethod.GET)
	@ResponseBody
	public Result checkUsername(String username){
		return iMemberService.checkUsername(username);
	}
	
	@RequestMapping(value = "/checkMobile", method = RequestMethod.GET)
	@ResponseBody
	public Result checkMobile(String mobile){
		return iMemberService.checkMobile(mobile);
	}
	
	@RequestMapping(value = "/subRegister", method = RequestMethod.GET)
	@ResponseBody
	public Result subRegister(String username , String password , String mobile , String verifyCode,HttpSession session){
		Result result = iMemberService.register(username, mobile, password, verifyCode);
		if(result.getCode() == 200){
			session.setAttribute("currMember", result.getResult());
		}
		return result;
	}
	
}
