package com.tamguo.service;

import java.util.List;

import com.tamguo.model.SchoolEntity;

public interface ISchoolService {

	public List<SchoolEntity> findEliteSchoolPaper(String shcoolId);
	
	public List<SchoolEntity> findEliteSchool();
	
}
