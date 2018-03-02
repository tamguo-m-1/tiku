package com.tamguo.dao;

import com.tamguo.model.SysRoleEntity;
import com.tamguo.mybatis.dao.BaseDao;

public interface SysRoleMapper extends BaseDao<SysRoleEntity> {
	
	void save(SysRoleEntity sysRoleEntity);

}
