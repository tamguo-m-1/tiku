package com.tamguo.web;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tamguo.service.IEmailService;
import com.tamguo.util.Result;
import com.tamguo.util.TamguoConstant;

@Controller
public class EmailController {
	
	@Autowired
	private IEmailService iEmailService;

	@RequestMapping(value = {"email/sendFindPasswordEmail.html"}, method = RequestMethod.GET)
	@ResponseBody
	public Result sendFindPasswordEmail(String email){
		try {
			Integer result = iEmailService.sendFindPasswordEmail(email , TamguoConstant.ALIYUN_MAIL_SUBJECT_FINDPASSWORD);
			if(result == 0){
				return Result.result(200, null, "服务器异常");
			}
		} catch (EmailException e) {
			e.printStackTrace();
		}
		return Result.result(500, null, "服务器异常");
	}
	
}
