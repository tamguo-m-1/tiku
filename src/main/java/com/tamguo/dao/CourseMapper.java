package com.tamguo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.tamguo.model.CourseEntity;
import com.tamguo.mybatis.dao.BaseDao;

public interface CourseMapper extends BaseDao<CourseEntity>{

	List<CourseEntity> findBySubjectId(String subjectId);

	Page<CourseEntity> queryPageByName(@Param(value="name")String name);

}
