package com.tamguo.dao;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.tamguo.model.SubjectEntity;
import com.tamguo.mybatis.dao.BaseDao;

public interface SubjectMapper extends BaseDao<SubjectEntity>{

	Page<SubjectEntity> queryPage(@Param(value="name")String name);

}
