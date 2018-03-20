package com.tamguo.service;

import org.apache.commons.mail.EmailException;

public interface IEmailService {

	public Integer sendFindPasswordEmail(String email , String subject) throws EmailException;
	
}
