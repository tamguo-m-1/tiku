package com.tamguo.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tamguo.dao.SysUserMapper;
import com.tamguo.dao.SysUserRoleMapper;
import com.tamguo.model.SysUserEntity;
import com.tamguo.service.ISysUserRoleService;
import com.tamguo.service.ISysUserService;
import com.tamguo.util.DateUtil;
import com.tamguo.util.ShaEncrypt;

@Service
public class SysUserService implements ISysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;

	@Autowired
	private ISysUserRoleService sysUserRoleService;

	@Override
	public List<String> queryUserAllPerms(Long userId) {
		return sysUserRoleMapper.queryUserAllPerms(userId);
	}

	@Override
	public SysUserEntity queryByUserName(String username) {
		return sysUserMapper.queryByUserName(username);
	}

	@Override
	public Page<SysUserEntity> listSysUser(SysUserEntity sysUser, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<SysUserEntity> pageList = (Page<SysUserEntity>) sysUserMapper.listSysUser(sysUser);
		return pageList;
	}

	@Override
	public SysUserEntity selectById(Long userId) {
		return sysUserMapper.select(String.valueOf(userId));
	}

	@Override
	@Transactional
	public void save(SysUserEntity user) {
		user.setCreateTime(DateUtil.getTime());
		// sha256加密
		user.setPassword(ShaEncrypt.SHA256(user.getPassword()));
		sysUserMapper.insert(user);
		// 保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());

	}

	@Override
	@Transactional
	public void update(SysUserEntity user) {
		if (StringUtils.isEmpty(user.getPassword())) {
			user.setPassword(null);
		} else {
			user.setPassword(ShaEncrypt.SHA256(user.getPassword()));
		}
		sysUserMapper.update(user);

		// 保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Override
	public void deleteBatch(String[] userIds) {
		sysUserMapper.deleteByIds(Arrays.asList(userIds));
	}

	@Override
	@Transactional
	public int updatePassword(Long userId, String password, String newPassword) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("password", password);
		map.put("newPassword", newPassword);
		return sysUserMapper.updatePassword(map);
	}

	@Override
	public int updateLastLoginTime(String userId) {
		SysUserEntity user =  sysUserMapper.select(userId);
		user.setLastLoginTime(DateUtil.getTime());
		
		return sysUserMapper.update(user);
	}

}
