package com.tamguo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tamguo.dao.AreaMapper;
import com.tamguo.model.AreaEntity;
import com.tamguo.service.IAreaService;

@Service
public class AreaService implements IAreaService{
	
	@Autowired
	private AreaMapper areaMapper;
	
	@Override
	public List<AreaEntity> findAll() {
		return areaMapper.selectAll();
	}
	
}
