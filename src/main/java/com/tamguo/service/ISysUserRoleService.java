package com.tamguo.service;

import java.util.List;



/**
 * 用户角色关系
 *
 */
public interface ISysUserRoleService {
	
	void saveOrUpdate(Long userId, List<Long> roleIdList);
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);
}
