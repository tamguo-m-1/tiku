package com.tamguo.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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

	@Override
	public List<SubjectEntity> getSubjectTree() {
		return subjectMapper.selectAll();
	}

	@Override
	public JSONArray getCourseTree() {
		JSONArray courseTree = new JSONArray();
		
		List<SubjectEntity> subjectList = subjectMapper.selectAll();
		for(int i=0 ; i<subjectList.size() ; i++){
			SubjectEntity subject = subjectList.get(i);
			JSONObject node = new JSONObject();
			node.put("uid", "s" + subject.getUid());
			node.put("name", subject.getName());
			node.put("parentId", "-1");
			courseTree.add(node);
			List<CourseEntity> courseList = courseMapper.findBySubjectId(subject.getUid());
			for(int k=0 ; k<courseList.size() ; k++){
				CourseEntity course = courseList.get(k);
				
				node = new JSONObject();
				node.put("uid", course.getUid());
				node.put("name", course.getName());
				node.put("parentId", "s" + subject.getUid());
				courseTree.add(node);
			}
		}
		return courseTree;
	}
	
}
