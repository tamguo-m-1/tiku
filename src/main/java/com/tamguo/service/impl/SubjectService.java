package com.tamguo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tamguo.dao.SubjectMapper;
import com.tamguo.model.SubjectEntity;
import com.tamguo.service.ISubjectService;

@Service
public class SubjectService implements ISubjectService{

	@Autowired
	private SubjectMapper subjectMapper;

	@Override
	public SubjectEntity find(String uid) {
		return subjectMapper.select(uid);
	}
	
}
