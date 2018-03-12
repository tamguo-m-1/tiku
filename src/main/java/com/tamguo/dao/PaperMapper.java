package com.tamguo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.tamguo.model.PaperEntity;
import com.tamguo.mybatis.dao.BaseDao;

public interface PaperMapper extends BaseDao<PaperEntity>{

	List<PaperEntity> findByTypeAndAreaId(@Param(value="type")String type, @Param(value="areaId")String areaId);
	
	List<PaperEntity> findByAreaId(@Param(value="areaId") String areaId);

	List<PaperEntity> findBySchoolId(@Param(value="schoolId")String schoolId);

	Page<PaperEntity> findList(@Param(value="courseId")String courseId, @Param(value="paperType")String paperType, 
			@Param(value="year")String year, @Param(value="area")String area);

	List<PaperEntity> findPaperByAreaId(@Param(value="areaId")String areaId , @Param(value="type")String type);

	Long getPaperTotal();

}
