package com.tamguo.service.impl;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

import com.tamguo.service.IEmailService;
import com.tamguo.util.TamguoConstant;

@Service
public class EmailService implements IEmailService{

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
		mail.setHtmlMsg("<html><body>找回密码</body><html>");
		mail.send();
		return 0;
	}

}
