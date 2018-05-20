package com.tamguo.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tamguo.dao.ChapterMapper;
import com.tamguo.model.ChapterEntity;
import com.tamguo.service.IChapterService;
import com.tamguo.util.TamguoConstant;

@Service
public class ChapterService implements IChapterService{
	
	@Autowired
	private ChapterMapper chapterMapper;

	@Override
	public List<ChapterEntity> findCourseChapter(String courseId) {
		List<ChapterEntity> chapterList = chapterMapper.findByCourseId(courseId);
		
		// 获取根chapter UID
		String rootUid = StringUtils.EMPTY;
		for(int i=0 ; i<chapterList.size() ; i++){
			ChapterEntity chapter = chapterList.get(i);
			if(chapter.getParentId().equals(TamguoConstant.CHAPTER_DEFAULT_ROOT_UID)){
				rootUid = chapter.getUid();
			}
		}
		// 获取第一层结构
		List<ChapterEntity> entitys = new ArrayList<>();
		for(int i=0 ; i<chapterList.size() ; i++){
			ChapterEntity chapter = chapterList.get(i);
			if(rootUid.equals(chapter.getParentId())){
				entitys.add(chapter);
			}
		}
		for(int i=0 ; i<entitys.size() ; i++){
			ChapterEntity entity = entitys.get(i);
			List<ChapterEntity> childs = new ArrayList<>();
			for(int k=0 ; k<chapterList.size() ; k++){
				ChapterEntity chapter = chapterList.get(k);
				if(entity.getUid().equals(chapter.getParentId())){
					childs.add(chapter);
				}
			}
			entity.setChildChapterList(childs);
		}
		for(int i=0 ; i<entitys.size() ; i++){
			List<ChapterEntity> childs = entitys.get(i).getChildChapterList();
			for(int k=0 ; k<childs.size() ; k++){
				ChapterEntity child = childs.get(k);
				List<ChapterEntity> tmpChilds = new ArrayList<>();
				for(int n=0 ; n<chapterList.size() ; n++){
					ChapterEntity chapter = chapterList.get(n);
					if(child.getUid().equals(chapter.getParentId())){
						tmpChilds.add(chapter);
					}
				}
				child.setChildChapterList(tmpChilds);
			}
		}
		return entitys;
	}

	@Override
	public ChapterEntity findById(String uid) {
		return chapterMapper.select(uid);
	}

	@Override
	public ChapterEntity findNextPoint(String uid , Integer orders) {
		return chapterMapper.findNextPoint(uid , orders);
	}

	@Transactional(readOnly=false)
	@Override
	public List<ChapterEntity> getChapterTree(String courseId) {
		if(StringUtils.isEmpty(courseId) || "null".equals(courseId)){
			return rootChapterNode();
		}
		List<ChapterEntity> list = chapterMapper.findByCourseId(courseId);
		if(CollectionUtils.isEmpty(list)) {
			return rootChapterNode();
		}
		return list;
	}

	private List<ChapterEntity> rootChapterNode(){
		ChapterEntity chapter = new ChapterEntity();
		chapter.setCourseId(TamguoConstant.CHAPTER_DEFAULT_ROOT_UID);
		chapter.setOrders(0);
		chapter.setPointNum(0);
		chapter.setQuestionNum(0);
		chapter.setUid("0");
		chapter.setName("章节根目录");
		chapter.setParentId(TamguoConstant.CHAPTER_DEFAULT_ROOT_UID);
		return Arrays.asList(chapter);
	}

}
