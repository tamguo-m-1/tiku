package com.tamguo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.tamguo.model.ChapterEntity;
import com.tamguo.mybatis.dao.BaseDao;

public interface ChapterMapper extends BaseDao<ChapterEntity>{

	List<ChapterEntity> findByCourseId(@Param(value="courseId") String courseId);
	
	List<ChapterEntity> findByParentId(@Param(value="parentId") String parentId);

	ChapterEntity findNextPoint(@Param(value="uid")String uid , @Param(value="orders")Integer orders);

	void deleteByCourseId(@Param(value="courseId")String courseId);

}
