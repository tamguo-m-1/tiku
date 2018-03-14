package com.tamguo.service;

import com.tamguo.util.Result;

public interface IMemberService {

	public Result login(String username , String password , String captcha);
	
}
