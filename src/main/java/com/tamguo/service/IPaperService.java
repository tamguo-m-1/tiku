package com.tamguo.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.tamguo.model.PaperEntity;

public interface IPaperService {

	List<PaperEntity> findHistoryPaper();

	List<PaperEntity> findSimulationPaper();

	List<PaperEntity> findHotPaper(String areaId);

	Page<PaperEntity> findList(String courseId, String paperType,
			String year, String area , Integer pageNum);
	
	PaperEntity find(String paperId);

	List<PaperEntity> findPaperByAreaId(String areaId , String type);

	Long getPaperTotal();

	Page<PaperEntity> list(String name, Integer page, Integer limit);

	PaperEntity select(String paperId);

	void deleteByIds(String[] paperIds);

	void save(PaperEntity paper);

	void update(PaperEntity paper);
	
	// 会员部分
	
	List<PaperEntity> findByCreaterId(String createrId);

	void updatePaperName(String paperId, String name);

	void deletePaper(String paperId);

	void addPaperQuestionInfo(String paperId, String title, String name,
			String type);

	void updatePaperQuestionInfo(String paperId, String title, String name,
			String type, String cuid);

	void deletePaperQuestionInfoBtn(String paperId, String cuid);

	Page<PaperEntity> memberPaperList(String name, String memberId , Integer page, Integer limit);

}
