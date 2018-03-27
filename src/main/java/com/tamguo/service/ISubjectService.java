package com.tamguo.service;

import com.github.pagehelper.Page;
import com.tamguo.model.SubjectEntity;

public interface ISubjectService {

	public SubjectEntity find(String uid);
	
	public Page<SubjectEntity> list(String name , Integer pageNum , Integer pageSize);

	public void update(SubjectEntity subject);

	public void save(SubjectEntity subject);
	
}
