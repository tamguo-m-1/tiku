package com.tamguo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tamguo.dao.SysUserRoleMapper;
import com.tamguo.model.SysUserRoleEntity;
import com.tamguo.mybatis.utils.Condition;
import com.tamguo.service.ISysUserRoleService;

/**
 * 用户与角色对应关系
 *
 */
@Service("sysUserRoleService")
public class SysUserRoleService implements ISysUserRoleService {
	@Autowired
    private SysUserRoleMapper sysUserRoleMapper;

	@Override
	public void saveOrUpdate(Long userId, List<Long> roleIdList) {
		if(roleIdList.size() == 0){
			return ;
		}
		
		//先删除用户与角色关系
		Condition<SysUserRoleEntity> condition = new Condition<SysUserRoleEntity>(){};
		condition.andEqualTo("userId", userId);
		sysUserRoleMapper.deleteByCondition(condition);
		
		//保存用户与角色关系
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("roleIdList", roleIdList);
		sysUserRoleMapper.saveUserRole(map);
	}

	@Override
	public List<Long> queryRoleIdList(Long userId) {
		return sysUserRoleMapper.queryRoleIdList(userId);
	}
}
