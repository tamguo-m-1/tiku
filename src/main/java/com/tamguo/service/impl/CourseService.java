package com.tamguo.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tamguo.dao.CourseMapper;
import com.tamguo.dao.redis.CacheService;
import com.tamguo.model.CourseEntity;
import com.tamguo.service.ICourseService;
import com.tamguo.util.TamguoConstant;

@Service
public class CourseService implements ICourseService{
	
	@Autowired
	private CourseMapper courseMapper;
	@Autowired
	private CacheService cacheService;

	@SuppressWarnings("unchecked")
	@Override
	public List<CourseEntity> findGaokaoArea(String subjectId) {
		List<CourseEntity> courseList = (List<CourseEntity>) cacheService.getObject(TamguoConstant.GAOKAO_COURSE_AREA);
		courseList = null;
		if(courseList == null || courseList.isEmpty()){
			courseList = courseMapper.findBySubjectId(subjectId);
			cacheService.setObject(TamguoConstant.GAOKAO_COURSE_AREA, courseList , 2 * 60 * 60);
		}
		return courseList;
	}

	@Override
	public List<CourseEntity> findBySubjectId(String subjectId) {
		return courseMapper.findBySubjectId(subjectId);
	}

	@Override
	public CourseEntity find(String uid) {
		return courseMapper.select(uid);
	}

	@Override
	public Page<CourseEntity> list(String name, Integer page, Integer limit) {
		PageHelper.startPage(page, limit);
		return courseMapper.queryPageByName(name);
	}

	@Override
	public CourseEntity select(String courseId) {
		return courseMapper.select(courseId);
	}

	@Override
	public void deleteByIds(String[] courseIds) {
		courseMapper.deleteByIds(Arrays.asList(courseIds));
	}

	@Override
	public void save(CourseEntity course) {
		courseMapper.insert(course);
	}

	@Override
	public void update(CourseEntity course) {
		courseMapper.update(course);
	}

}
