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
}
