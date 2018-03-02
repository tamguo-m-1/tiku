package com.tamguo.service;

import java.util.List;
import com.tamguo.model.ChapterEntity;

public interface IChapterService {

	// 获取科目章节
	public List<ChapterEntity> findCourseChapter(String courseId);
	
	// 获取当前章节
	public ChapterEntity findById(String uid);
	
	// 获取下一个知识点
	public ChapterEntity findNextPoint(String uid , Integer orders);
	
}
