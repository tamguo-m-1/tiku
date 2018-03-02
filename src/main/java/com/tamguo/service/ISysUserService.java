package com.tamguo.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.tamguo.model.SysUserEntity;

public interface ISysUserService {

	List<String> queryUserAllPerms(Long userId);

	SysUserEntity queryByUserName(String username);
	
	Page<SysUserEntity> listSysUser(SysUserEntity sysUser,int pageNum,int pageSize);
	
	SysUserEntity selectById(Long userId);
	
	/**
	 * 保存用户
	 */
	void save(SysUserEntity user);
	
	/**
	 * 修改用户
	 */
	void update(SysUserEntity user);
	
	/**
	 * 删除用户
	 */
	void deleteBatch(String[] userIds);
	
	/**
	 * 修改密码
	 * @param userId       用户ID
	 * @param password     原密码
	 * @param newPassword  新密码
	 */
	int updatePassword(Long userId, String password, String newPassword);
	
	/**
	 * 写入最后登录时间
	 * @param userId
	 * @return
	 */
	int updateLastLoginTime(String userId);
}
