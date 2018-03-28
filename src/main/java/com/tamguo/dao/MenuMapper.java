package com.tamguo.dao;

import java.util.List;

import com.github.pagehelper.Page;
import com.tamguo.model.MenuEntity;
import com.tamguo.model.SubjectEntity;
import com.tamguo.mybatis.dao.BaseDao;

public interface MenuMapper extends BaseDao<MenuEntity>{

	public List<MenuEntity> findFatherMenus();
	
	public List<MenuEntity> findMenuByParentId(String parentId);

	public List<MenuEntity> findAllFatherMenus();

	public List<MenuEntity> findLeftFatherMenus();

	public List<MenuEntity> findFooterFatherMenus();

	public Page<SubjectEntity> findByName(String name);
	
}
