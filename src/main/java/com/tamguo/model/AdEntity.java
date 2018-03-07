package com.tamguo.model;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;

/**
 * The persistent class for the tiku_ad database table.
 * 
 */
@Entity
@Table(name="tiku_ad")
@NamedQuery(name="AdEntity.findAll", query="SELECT a FROM AdEntity a")
public class AdEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String uid;
	
	@Column(name="business_key")
	private String businessKey;

	@Column(name="name")
	private String name;
	
	@Column(name="ad_info")
	private String adInfo;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAdInfo() {
		return adInfo;
	}

	public void setAdInfo(String adInfo) {
		this.adInfo = adInfo;
	}
	
	public JSONArray getAds(){
		if(StringUtils.isEmpty(getAdInfo())){
			return null;
		}
		return JSONArray.parseArray(getAdInfo());
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

}