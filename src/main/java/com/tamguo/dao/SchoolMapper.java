package com.tamguo.dao;

import java.util.List;

import com.tamguo.model.SchoolEntity;
import com.tamguo.mybatis.dao.BaseDao;

public interface SchoolMapper extends BaseDao<SchoolEntity>{

	List<SchoolEntity> findByAreaId(String beijingAreaId);
	
}
