package com.tamguo.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.tamguo.model.SysMenuEntity;

public interface ISysMenuService {
    
	Page<SysMenuEntity> queryList(Map<String, Object> map,int pageNum,int pageSize);
    
    List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList);
    
    List<SysMenuEntity> getUserMenuList(Long userId);
    
    SysMenuEntity getMenuByUrl(String url);
    
    /**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenuEntity> queryNotButtonList();
	
	
	/**
	 * 查询菜单
	 */
	SysMenuEntity queryObject(Long menuId);
	
	/**
	 * 保存菜单
	 */
	void save(SysMenuEntity menu);
	
	/**
	 * 修改
	 */
	void update(SysMenuEntity menu);
	
	/**
	 * 删除
	 */
	void deleteBatch(String[] menuIds);
	
}
