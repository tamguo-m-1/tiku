package com.tamguo.service;

import java.util.List;



/**
 * 角色菜单关系
 *
 */
public interface ISysRoleMenuService {
	
	void saveOrUpdate(Long roleId, List<Long> menuIdList);
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<Long> queryMenuIdList(Long roleId);
	
}
