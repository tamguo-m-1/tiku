package com.tamguo.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tamguo.dao.PaperMapper;
import com.tamguo.dao.redis.CacheService;
import com.tamguo.model.PaperEntity;
import com.tamguo.service.IPaperService;
import com.tamguo.util.TamguoConstant;

@Service
public class PaperService implements IPaperService{
	
	@Autowired
	private PaperMapper paperMapper;
	@Autowired
	private CacheService cacheService;

	@SuppressWarnings("unchecked")
	@Override
	public List<PaperEntity> findHistoryPaper() {
		PageHelper.startPage(1, 6);
		List<PaperEntity> paperList = (List<PaperEntity>) cacheService.getObject(TamguoConstant.HISTORY_PAPER);
		if(paperList == null || paperList.isEmpty()){
			paperList = paperMapper.findByTypeAndAreaId(TamguoConstant.ZHENGTI_PAPER_ID , TamguoConstant.BEIJING_AREA_ID);
			cacheService.setObject(TamguoConstant.ZHENGTI_PAPER_ID, paperList , 2 * 60 * 60);
		}
		return paperList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PaperEntity> findSimulationPaper() {
		PageHelper.startPage(1, 6);
		List<PaperEntity> paperList = (List<PaperEntity>) cacheService.getObject(TamguoConstant.SIMULATION_PAPER);
		if(paperList == null || paperList.isEmpty()){
			paperList = paperMapper.findByTypeAndAreaId(TamguoConstant.SIMULATION_PAPER_ID , TamguoConstant.BEIJING_AREA_ID);
			cacheService.setObject(TamguoConstant.SIMULATION_PAPER, paperList , 2 * 60 * 60);
		}
		return paperList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PaperEntity> findHotPaper(String areaId) {
		PageHelper.startPage(1, 10);
		List<PaperEntity> paperList = (List<PaperEntity>) cacheService.getObject(TamguoConstant.HOT_PAPER);
		paperList = null;
		if(paperList == null || paperList.isEmpty()){
			paperList = paperMapper.findByAreaId(areaId);
			cacheService.setObject(TamguoConstant.HOT_PAPER, paperList , 2 * 60 * 60);
		}
		return paperList;
	}

	@Override
	public Page<PaperEntity> findList(String courseId,
			String paperType, String year, String area , Integer pageNum) {
		PageHelper.startPage(pageNum, TamguoConstant.DEFAULT_PAGE_SIZE);
		return paperMapper.findList(courseId , paperType , year , area);
	}

	@Override
	public PaperEntity find(String paperId) {
		return paperMapper.select(paperId);
	}

	@Override
	public List<PaperEntity> findPaperByAreaId(String areaId , String type) {
		if("n".equals(type)){
			return this.findHotPaper(areaId);
		}
		PageHelper.startPage(1, 8);
		return paperMapper.findPaperByAreaId(areaId , type);
	}

	@Override
	public Long getPaperTotal() {
		return paperMapper.getPaperTotal();
	}

	@Override
	public Page<PaperEntity> list(String name, Integer page, Integer limit) {
		PageHelper.startPage(page, limit);
		if(!StringUtils.isEmpty(name)){
			name = "%" + name + "%";
		}
		return paperMapper.queryPageByName(name);
	}

	@Override
	public PaperEntity select(String paperId) {
		return paperMapper.select(paperId);
	}

	@Override
	public void deleteByIds(String[] paperIds) {
		paperMapper.deleteByIds(Arrays.asList(paperIds));
	}

	@Override
	public void save(PaperEntity paper) {
		paperMapper.insert(paper);
	}

	@Override
	public void update(PaperEntity paper) {
		paperMapper.update(paper);
	}

	@Override
	public List<PaperEntity> findByCreaterId(String createrId) {
		return paperMapper.findByCreaterId(createrId);
	}

	@Transactional(readOnly=false)
	@Override
	public void updatePaperName(String paperId, String name) {
		PaperEntity paper = paperMapper.select(paperId);
		paper.setName(name);
		paperMapper.update(paper);
	}

	@Override
	public void deletePaper(String paperId) {
		paperMapper.delete(paperId);
	}

	@Transactional(readOnly=false)
	@Override
	public void addPaperQuestionInfo(String paperId, String title,
			String name, String type) {
		PaperEntity paper = paperMapper.select(paperId);
		String questionInfo = paper.getQuestionInfo();
		
		JSONArray qList = JSONArray.parseArray(questionInfo);
		JSONObject entity = new JSONObject();
		entity.put("name", name);
		entity.put("title", title);
		entity.put("type", type);
		qList.add(entity);
		
		// 处理uid 问题
		for(int i=0 ; i<qList.size(); i++){
			JSONObject q = qList.getJSONObject(i);
			q.put("uid", i+1);
		}
		
		paper.setQuestionInfo(qList.toString());
		paperMapper.update(paper);
	}

	@Transactional(readOnly=false)
	@Override
	public void updatePaperQuestionInfo(String paperId, String title,
			String name, String type, String cuid) {
		PaperEntity paper = paperMapper.select(paperId);
		String questionInfo = paper.getQuestionInfo();
		JSONArray qList = JSONArray.parseArray(questionInfo);
		for(int i =0 ; i<qList.size() ; i++){
			JSONObject q = qList.getJSONObject(i);
			if(q.getString("uid").equals(cuid)){
				q.put("name", name);
				q.put("title", title);
				q.put("type", type);
			}
		}
		
		paper.setQuestionInfo(qList.toString());
		paperMapper.update(paper);
	}

	@Override
	public void deletePaperQuestionInfoBtn(String paperId, String cuid) {
		PaperEntity paper = paperMapper.select(paperId);
		String questionInfo = paper.getQuestionInfo();
		JSONArray qList = JSONArray.parseArray(questionInfo);
		for(int i =0 ; i<qList.size() ; i++){
			JSONObject q = qList.getJSONObject(i);
			if(q.getString("uid").equals(cuid)){
				qList.remove(i);
			}
		}
		
		// 处理uid 问题
		for(int i=0 ; i<qList.size(); i++){
			JSONObject q = qList.getJSONObject(i);
			q.put("uid", i+1);
		}
				
		paper.setQuestionInfo(qList.toString());
		paperMapper.update(paper);
	}

	@Override
	public Page<PaperEntity> memberPaperList(String name , String memberId , Integer page,
			Integer limit) {
		PageHelper.startPage(page, limit);
		if(!StringUtils.isEmpty(name)){
			name = "%" + name + "%";
		}
		return paperMapper.queryPageByNameAndCreatorId(name , memberId);
	}

}
