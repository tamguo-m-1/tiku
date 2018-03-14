package com.tamguo.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.code.kaptcha.Constants;
import com.tamguo.dao.MemberMapper;
import com.tamguo.model.MemberEntity;
import com.tamguo.service.IMemberService;
import com.tamguo.util.Result;
import com.tamguo.util.ShiroUtils;

@Service
public class MemberService implements IMemberService{
	
	@Autowired
	private MemberMapper memberMapper;

	@Override
	public Result login(String username, String password , String captcha) {
		MemberEntity member = memberMapper.findByUsername(username);
		if(member == null){
			return Result.result(201, member, "用户名或密码有误，请重新输入或找回密码");
		}
		Integer loginFailureCount = member.getLoginFailureCount();
		if(new Sha256Hash(password).toHex().equals(member.getPassword())){
			loginFailureCount = 0;
		}else {
			loginFailureCount ++;
		}
		member.setLoginFailureCount(loginFailureCount);
		memberMapper.update(member);
		if(!new Sha256Hash(password).toHex().equals(member.getPassword())){
			if(loginFailureCount >= 5){
				return Result.result(203, member, "用户名或密码有误，错误次数超过五次，启用验证码");
			}
			return Result.result(202, member, "用户名或密码有误，请重新输入或找回密码");
		}
		if(!StringUtils.isEmpty(captcha)){
			String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
			if (!captcha.equalsIgnoreCase(kaptcha)) {
				return Result.result(204, member, "验证码错误");
			}
		}
		return Result.result(200, member, "登录成功");
	}
	
}
