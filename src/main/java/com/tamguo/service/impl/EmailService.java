package com.tamguo.service.impl;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tamguo.dao.redis.CacheService;
import com.tamguo.service.IEmailService;
import com.tamguo.util.TamguoConstant;

@Service
public class EmailService implements IEmailService{
	
	@Autowired
	private CacheService cacheService;
	
	@Override
	public Integer sendFindPasswordEmail(String email , String subject) throws EmailException {
		HtmlEmail mail = new HtmlEmail();
		mail.setHostName(TamguoConstant.ALIYUN_SMTP_HOST_NAME);
		mail.setSmtpPort(TamguoConstant.ALIYUN_SMTP_HOST_PORT);
		mail.setAuthentication(TamguoConstant.ALIYUN_MAIL_ACCOUNT, TamguoConstant.ALIYUN_MAIL_PASSWORD);
		mail.setSSLOnConnect(true);
		mail.setFrom(TamguoConstant.ALIYUN_MAIL_ACCOUNT, "探果网");
		mail.addTo(email);
		mail.setSubject(subject);
		mail.setCharset("UTF-8");
		Integer vcode = (int) ((Math.random()*9+1)*100000);  
		mail.setHtmlMsg("探果网找回密码验证码："+vcode);
		mail.send();
		
		cacheService.setObject(TamguoConstant.ALIYUN_MAIL_FIND_PASSWORD_PREFIX + email , vcode.toString() , 3 * 60);
		return 0;
	}

}
