package com.tamguo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
