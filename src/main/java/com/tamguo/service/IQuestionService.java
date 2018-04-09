package com.tamguo.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.tamguo.model.QuestionEntity;

public interface IQuestionService {

	/** 根据章节获取问题 */
	public Page<QuestionEntity> findByChapterId(String chapterId ,  Integer offset ,  Integer limit);
	
	public QuestionEntity findById(String uid);

	public List<QuestionEntity> findPaperQuestion(String paperId);

	public Page<QuestionEntity> list(String name, Integer page, Integer limit);

	public QuestionEntity select(String questionId);

	public void deleteBatch(String[] questionIds);
	
}
