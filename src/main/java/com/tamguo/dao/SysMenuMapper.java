package com.tamguo.dao;

import java.util.List;
import java.util.Map;

import com.tamguo.model.SysMenuEntity;
import com.tamguo.mybatis.dao.BaseDao;

public interface SysMenuMapper extends BaseDao<SysMenuEntity> {

	List<SysMenuEntity> queryList(Map<String, Object> map);

	List<SysMenuEntity> queryListParentId(Long parentId);
	
	List<SysMenuEntity> queryNotButtonList();

}
