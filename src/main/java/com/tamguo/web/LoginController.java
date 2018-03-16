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
public class LoginController {
	
	@Autowired
	private IMemberService iMemberService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseBody
    public Result login(String username , String password , String captcha, ModelAndView model , HttpSession session) {
		Result result = iMemberService.login(username, password , captcha);
		if(result.getCode() == 200){
			session.setAttribute("currMember", result.getResult());
		}
		return result;
    }

}
