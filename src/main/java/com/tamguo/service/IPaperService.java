package com.tamguo.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.tamguo.model.PaperEntity;

public interface IPaperService {

	List<PaperEntity> findHistoryPaper();

	List<PaperEntity> findSimulationPaper();

	List<PaperEntity> findHotPaper();

	Page<PaperEntity> findList(String courseId, String paperType,
			String year, String area , Integer pageNum);

}
