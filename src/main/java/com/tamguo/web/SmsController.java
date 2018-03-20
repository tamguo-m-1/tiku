package com.tamguo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyuncs.exceptions.ClientException;
import com.tamguo.service.ISmsService;
import com.tamguo.util.Result;

@Controller
public class SmsController {
	
	@Autowired
	ISmsService iSmsService;

	@RequestMapping(value = {"sms/sendFindPasswordSms.html"}, method = RequestMethod.GET)
	@ResponseBody
	public Result sendFindPasswordSms(String mobile){
		try {
			return iSmsService.sendFindPasswordSms(mobile);
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return Result.result(500, null, "");
	}
	
}
