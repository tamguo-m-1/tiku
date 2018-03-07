package com.tamguo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tamguo.dao.AdMapper;
import com.tamguo.dao.redis.CacheService;
import com.tamguo.model.AdEntity;
import com.tamguo.service.IAdService;
import com.tamguo.util.TamguoConstant;

@Service
public class AdService implements IAdService{
	
	@Autowired
	AdMapper adMapper;
	@Autowired
	CacheService cacheService;

	@SuppressWarnings("unchecked")
	@Override
	public List<AdEntity> findAll() {
		List<AdEntity> adList = (List<AdEntity>) cacheService.getObject(TamguoConstant.ALL_AD);
		adList = null;
		if(adList == null || adList.isEmpty()){
			adList = adMapper.selectAll();
			cacheService.setObject(TamguoConstant.ALL_AD, adList , 2 * 60 * 60);
		}
		return adList;
	}
	
}
