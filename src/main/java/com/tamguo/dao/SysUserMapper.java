package com.tamguo.dao;

import java.util.List;
import java.util.Map;

import com.tamguo.model.SysUserEntity;
import com.tamguo.mybatis.dao.BaseDao;

public interface SysUserMapper extends BaseDao<SysUserEntity> {

	SysUserEntity queryByUserName(String username);
	
	List<SysUserEntity> listSysUser(SysUserEntity sysUser);
	
	/**
	 * 修改密码
	 */
	int updatePassword(Map<String, Object> map);

}
