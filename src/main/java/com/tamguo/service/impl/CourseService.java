package com.tamguo.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tamguo.dao.ChapterMapper;
import com.tamguo.dao.CourseMapper;
import com.tamguo.dao.redis.CacheService;
import com.tamguo.model.ChapterEntity;
import com.tamguo.model.CourseEntity;
import com.tamguo.service.ICourseService;
import com.tamguo.util.TamguoConstant;

@Service
public class CourseService implements ICourseService{
	
	@Autowired
	private CourseMapper courseMapper;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private ChapterMapper chapterMapper; 

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
		if(!StringUtils.isEmpty(name)) {
			name = "%" + name + "%";
		}
		return courseMapper.queryPageByName(name);
	}

	@Override
	public CourseEntity select(String courseId) {
		return courseMapper.select(courseId);
	}

	@Transactional(readOnly=false)
	@Override
	public void deleteByIds(String[] courseIds) {
		courseMapper.deleteByIds(Arrays.asList(courseIds));
		
		for(int i=0 ; i<courseIds.length ; i++){
			// 删除之前的章节
			chapterMapper.deleteByCourseId(courseIds[i]);
		}
	}

	@Transactional(readOnly=false)
	@Override
	public void save(CourseEntity course) {
		course.setUid(null);
		
		course.setSeoTitle(course.getName());
		course.setSeoKeywords(course.getName());
		course.setSeoDescription(course.getName());
		
		courseMapper.insert(course);
		
		// 更新章节
		List<ChapterEntity> chapterList = course.getChapterList();
		for(int i=0 ; i<chapterList.size() ; i++){
			ChapterEntity chapter = chapterList.get(i);
			if(TamguoConstant.CHAPTER_DEFAULT_ROOT_UID.equals(chapter.getParentId())){
				chapter.setName(course.getName());
			}
			String uid = chapter.getUid();
			
			chapter.setUid(null);
			chapter.setParentId(chapter.getParentId());
			chapter.setCourseId(course.getUid());
			chapterMapper.insert(chapter);
			
			for(int k=0 ; k<chapterList.size() ; k++){
				ChapterEntity c = chapterList.get(k);
				if(c.getParentId().equals(uid)){
					c.setParentId(chapter.getUid());
				}
			}
		}
	}

	@Transactional(readOnly=false)
	@Override
	public void update(CourseEntity course) {
		courseMapper.update(course);
		
		// 删除之前的章节
		chapterMapper.deleteByCourseId(course.getUid());
		// 更新章节
		List<ChapterEntity> chapterList = course.getChapterList();
		for(int i=0 ; i<chapterList.size() ; i++){
			ChapterEntity chapter = chapterList.get(i);
			if(TamguoConstant.CHAPTER_DEFAULT_ROOT_UID.equals(chapter.getParentId())){
				chapter.setName(course.getName());
			}
			String uid = chapter.getUid();
			
			chapter.setUid(null);
			chapter.setParentId(chapter.getParentId());
			chapter.setCourseId(course.getUid());
			chapterMapper.insert(chapter);
			
			for(int k=0 ; k<chapterList.size() ; k++){
				ChapterEntity c = chapterList.get(k);
				if(c.getParentId().equals(uid)){
					c.setParentId(chapter.getUid());
				}
			}
		}
	}

}
