package com.tamguo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tamguo.dao.MemberMapper;
import com.tamguo.model.MemberEntity;
import com.tamguo.service.IMemberService;
import com.tamguo.util.Result;

@Service
public class MemberService implements IMemberService{
	
	@Autowired
	private MemberMapper memberMapper;

	@Override
	public Result login(String username, String password) {
		MemberEntity member = memberMapper.findByUsername(username);
		if(member == null){
			return Result.result(201, null, "用户名或密码有误，请重新输入或找回密码");
		}
		if(!password.equals(member.getPassword())){
			return Result.result(202, null, "用户名或密码有误，请重新输入或找回密码");
		}
		if(password.equals(member.getPassword())){
			return Result.result(200, null, "登录成功");
		}
		return Result.result(299 , null , "服务异常");
	}
	
}
