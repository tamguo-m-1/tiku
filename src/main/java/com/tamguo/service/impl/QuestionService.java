package com.tamguo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tamguo.dao.QuestionMapper;
import com.tamguo.model.QuestionEntity;
import com.tamguo.service.IQuestionService;

@Service
public class QuestionService implements IQuestionService{
	
	@Autowired
	private QuestionMapper questionMapper;

	@Override
	public Page<QuestionEntity> findByChapterId(String chapterId  , Integer offset ,  Integer limit) {
		PageHelper.offsetPage(offset, limit);
		return questionMapper.findByChapterId(chapterId);
	}

	@Override
	public QuestionEntity findById(String uid) {
		return questionMapper.select(uid);
	}

	@Override
	public List<QuestionEntity> findPaperQuestion(String paperId) {
		return questionMapper.findByPaperId(paperId);
	}

}
