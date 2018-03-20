package com.tamguo.service.impl;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.code.kaptcha.Constants;
import com.tamguo.dao.MemberMapper;
import com.tamguo.dao.redis.CacheService;
import com.tamguo.model.MemberEntity;
import com.tamguo.service.IMemberService;
import com.tamguo.util.Result;
import com.tamguo.util.ShiroUtils;
import com.tamguo.util.TamguoConstant;

@Service
public class MemberService implements IMemberService{
	
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private CacheService cacheService;

	@Override
	public Result login(String username, String password , String captcha) {
		MemberEntity member = memberMapper.findByUsername(username);
		if(member == null){
			return Result.result(201, member, "用户名或密码有误，请重新输入或找回密码");
		}
		Integer loginFailureCount = member.getLoginFailureCount();		
		if(loginFailureCount == 3 && !new Sha256Hash(password).toHex().equals(member.getPassword())){
			this.updateLoginInfo(member , loginFailureCount++);
			return Result.result(203, member, "用户名或密码有误，错误次数超过三次，启用验证码！");
		}
		if(loginFailureCount > 3 && StringUtils.isEmpty(captcha)){
			this.updateLoginInfo(member , loginFailureCount++);
			return Result.result(204, member, "请输入验证码！");
		}
		if(loginFailureCount > 3){
			String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
			if (!captcha.equalsIgnoreCase(kaptcha)) {
				return Result.result(205, member, "验证码错误");
			}
		}
		if(!new Sha256Hash(password).toHex().equals(member.getPassword())){
			this.updateLoginInfo(member , loginFailureCount++);
			return Result.result(202, member, "用户名或密码有误，请重新输入或找回密码");
		}
		this.updateLoginInfo(member , 0);
		return Result.result(200, member, "登录成功");
	}
	
	private void updateLoginInfo(MemberEntity member , Integer loginFailureCount){
		member.setLoginFailureCount(loginFailureCount);
		memberMapper.update(member);
	}

	@Override
	public Result checkUsername(String username) {
		MemberEntity member = memberMapper.findByUsername(username);
		if(member != null){
			return Result.result(201, null, "该用户名已经存在");
		}
		return Result.result(200, null, "该用户名可用");
	}

	@Override
	public Result checkMobile(String mobile) {
		MemberEntity member = memberMapper.findByMobile(mobile);
		if(member != null){
			return Result.result(201, null, "该手机号已经存在");
		}
		return Result.result(200, null, "该手机号可用");
	}

	@Override
	public Result register(String username, String mobile, String password,
			String verifyCode) {
		MemberEntity m = memberMapper.findByUsername(username);
		if(m != null){
			return Result.result(201, null, "该用户已经存在");
		}
		m = memberMapper.findByMobile(mobile);
		if(m != null){
			return Result.result(202, null, "该手机号已经存在");
		}
		MemberEntity member = new MemberEntity();
		member.setAvatar(TamguoConstant.DEFAULT_MEMBER_AVATAR);
		member.setLoginFailureCount(0);
		member.setMobile(mobile);
		member.setPassword(new Sha256Hash(password).toHex());
		member.setUsername(username);
		memberMapper.insert(member);
		return Result.result(200, member, "注册成功");
	}

	@Override
	public Result checkAccount(String account) {
		if(StringUtils.isEmpty(account)){
			return Result.result(201, null, "帐号不存在！");
		}
		MemberEntity member = memberMapper.findByUsernameOrEmailOrMobile(account);
		if(member == null){
			return Result.result(201, null, "帐号不存在！");
		}
		return Result.result(200, null, "该帐号存在");
	}

	@Override
	public Result confirmAccount(String account, String veritycode) {
		if(StringUtils.isEmpty(account)){
			return Result.result(201, null, "帐号不存在！");
		}
		MemberEntity member = memberMapper.findByUsernameOrEmailOrMobile(account);
		if(member == null){
			return Result.result(201, null, "帐号不存在！");
		}
		String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
		if (!veritycode.equalsIgnoreCase(kaptcha)) {
			return Result.result(202, null, "验证码错误");
		}
		return Result.result(200, member, "该帐号存在");
	}

	@Override
	public Result securityCheck(String username , String vcode) {
		// TODO 安全验证
		String key = UUID.randomUUID().toString();
		cacheService.setObject(key,  username , 2 * 60 * 60);
		return Result.result(200, key, "安全验证通过");
	}

	@Override
	public Result resetPassword(String resetPasswordKey , String username , String password, String verifypwd) {
		if(cacheService.isExist(resetPasswordKey)){
			MemberEntity member = memberMapper.findByUsername(username);
			if(password.equals(verifypwd)){
				member.setPassword(new Sha256Hash(password).toHex());
				memberMapper.update(member);
			}
		}
		return Result.result(200, null, "更新成功");
	}
	
}
