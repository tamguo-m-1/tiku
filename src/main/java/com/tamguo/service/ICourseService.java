package com.tamguo.service;

import java.util.List;
import com.tamguo.model.CourseEntity;

public interface ICourseService {

	/** 高考专区*/
	List<CourseEntity> findGaokaoArea(String gaokaoSubjectId);
	
	/** 根据考试获取科目 */
	List<CourseEntity> findBySubjectId(String subjectId);

}
