package com.tamguo.service.impl;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Override
	public Page<QuestionEntity> list(String name, Integer page, Integer limit) {
		return questionMapper.queryPageByName(name);
	}

	@Override
	public QuestionEntity select(String questionId) {
		return questionMapper.select(questionId);
	}

	@Override
	public void deleteBatch(String[] questionIds) {
		questionMapper.deleteByIds(Arrays.asList(questionIds));
	}

	@Transactional(readOnly=false)
	@Override
	public void addQuestion(QuestionEntity question) {
		question.setSubjectId(BigInteger.valueOf(2));
		question.setChapterId(BigInteger.valueOf(71));
		question.setPaperId(BigInteger.valueOf(1));
		questionMapper.insert(question);
	}

}
