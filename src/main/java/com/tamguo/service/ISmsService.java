package com.tamguo.service;

import com.aliyuncs.exceptions.ClientException;
import com.tamguo.util.Result;

public interface ISmsService {

	public Result sendFindPasswordSms(String mobile) throws ClientException;
	
}
