package com.tamguo.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tamguo.dao.PaperMapper;
import com.tamguo.dao.QuestionMapper;
import com.tamguo.model.PaperEntity;
import com.tamguo.model.QuestionEntity;
import com.tamguo.service.IQuestionService;

@Service
public class QuestionService implements IQuestionService{
	
	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private PaperMapper paperMapper;

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
		return questionMapper.selectByUid(questionId);
	}

	@Override
	public void deleteBatch(String[] questionIds) {
		questionMapper.deleteByIds(Arrays.asList(questionIds));
	}

	@Transactional(readOnly=false)
	@Override
	public void addQuestion(QuestionEntity question) {
		PaperEntity paper = paperMapper.select(question.getPaperId().toString());
		question.setCourseId(paper.getCourseId());
		questionMapper.insert(question);
	}

	@Override
	public Page<QuestionEntity> queryQuestionList(QuestionEntity question , Integer page , Integer limit) {
		PageHelper.startPage(page, limit);
		if(!StringUtils.isEmpty(question.getReviewPoint())){
			question.setReviewPoint("%" + question.getReviewPoint() + "%");
		}
		return questionMapper.queryQuestionList(question.getQuestionType(),question.getUid() ,question.getReviewPoint());
	}

	@Transactional(readOnly=false)
	@Override
	public void save(QuestionEntity question) {
		questionMapper.insert(question);
	}

	@Transactional(readOnly=false)
	@Override
	public void update(QuestionEntity question) {
		questionMapper.update(question);
	}

}
