package com.tamguo.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tamguo.dao.CourseMapper;
import com.tamguo.dao.SubjectMapper;
import com.tamguo.model.CourseEntity;
import com.tamguo.model.SubjectEntity;
import com.tamguo.service.ISubjectService;

@Service
public class SubjectService implements ISubjectService{

	@Autowired
	private SubjectMapper subjectMapper;
	@Autowired
	private CourseMapper courseMapper;

	@Override
	public SubjectEntity find(String uid) {
		SubjectEntity subject = subjectMapper.select(uid);
		List<CourseEntity> courseList = courseMapper.findBySubjectId(uid);
		subject.setCourseList(courseList);
		return subject;
	}

	@Override
	public Page<SubjectEntity> list(String name , Integer pageNum , Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		if(!StringUtils.isEmpty(name)){
			name = "%"+name+"%";
		}
		return subjectMapper.queryPage(name);
	}

	@Override
	public void update(SubjectEntity subject) {
		SubjectEntity entity = subjectMapper.select(subject.getUid());
		entity.setName(subject.getName());
		if(!StringUtils.isEmpty(subject.getCourseId())){
			CourseEntity course = courseMapper.select(subject.getCourseId());
			entity.setCourseId(course.getUid());
			entity.setCourseName(course.getName());
		}
		subjectMapper.update(entity);
	}

	@Override
	public void save(SubjectEntity subject) {
		if(!StringUtils.isEmpty(subject.getCourseId())){
			CourseEntity course = courseMapper.select(subject.getCourseId());
			subject.setCourseId(course.getUid());
			subject.setCourseName(course.getName());
		}
		subjectMapper.insert(subject);
	}

	@Override
	public void deleteBatch(String[] subjectIds) {
		subjectMapper.deleteByIds(Arrays.asList(subjectIds));
	}
	
}
