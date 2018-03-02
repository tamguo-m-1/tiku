package com.tamguo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the tiku_chapter database table.
 * 
 */
@Entity
@Table(name="tiku_school")
@NamedQuery(name="SchoolEntity.findAll", query="SELECT c FROM SchoolEntity c")
public class SchoolEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String uid;
	
	@Column(name="area_id")
	private String areaId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="image")
	private String image;
	
	// 试卷
	private List<PaperEntity> paperList;

	public SchoolEntity() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public List<PaperEntity> getPaperList() {
		return paperList;
	}

	public void setPaperList(List<PaperEntity> paperList) {
		this.paperList = paperList;
	}

}