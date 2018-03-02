package com.tamguo.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tamguo.dao.SysRoleMapper;
import com.tamguo.model.SysRoleEntity;
import com.tamguo.service.ISysRoleMenuService;
import com.tamguo.service.ISysRoleService;
import com.tamguo.util.DateUtil;

/**
 * 角色
 * 
 */
@Service
public class SysRoleService implements ISysRoleService {
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private ISysRoleMenuService sysRoleMenuService;
	

	@Override
	public SysRoleEntity queryObject(Long roleId) {
		return sysRoleMapper.select(String.valueOf(roleId));
	}

	@Override
	public Page<SysRoleEntity> queryList(SysRoleEntity sysRoleEntity, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<SysRoleEntity> pageList = (Page<SysRoleEntity>) sysRoleMapper.selectByEntity(sysRoleEntity);
		return pageList;
	}

	@Override
	@Transactional
	public void save(SysRoleEntity role) {
		role.setCreateTime(DateUtil.getTime());
		sysRoleMapper.save(role);
		// 保存角色与菜单关系
		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
	}

	@Override
	@Transactional
	public void update(SysRoleEntity role) {
		sysRoleMapper.update(role);
		// 更新角色与菜单关系
		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
	}

	@Override
	@Transactional
	public void deleteBatch(String[] roleIds) {
		sysRoleMapper.deleteByIds(Arrays.asList(roleIds));
	}


}
