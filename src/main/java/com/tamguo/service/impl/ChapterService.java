package com.tamguo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tamguo.dao.ChapterMapper;
import com.tamguo.model.ChapterEntity;
import com.tamguo.service.IChapterService;

@Service
public class ChapterService implements IChapterService{
	
	@Autowired
	private ChapterMapper chapterMapper;

	@Override
	public List<ChapterEntity> findCourseChapter(String courseId) {
		List<ChapterEntity> chapterList = chapterMapper.findByCourseId(courseId);
		// 获取子章节
		for(ChapterEntity chapter : chapterList){
			List<ChapterEntity> childChapterList = chapterMapper.findByParentId(chapter.getUid());
			for(ChapterEntity c : childChapterList){
				List<ChapterEntity> cChapterList = chapterMapper.findByParentId(c.getUid());
				c.setChildChapterList(cChapterList);
			}
			chapter.setChildChapterList(childChapterList);

		}
		return chapterList;
	}

	@Override
	public ChapterEntity findById(String uid) {
		return chapterMapper.select(uid);
	}

	@Override
	public ChapterEntity findNextPoint(String uid , Integer orders) {
		return chapterMapper.findNextPoint(uid , orders);
	}

}
