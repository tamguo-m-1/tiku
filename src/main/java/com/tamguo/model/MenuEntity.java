package com.tamguo.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the tiku_subject database table.
 * 
 */
@Entity
@Table(name="tiku_menu")
@NamedQuery(name="MenuEntity.findAll", query="SELECT s FROM MenuEntity s")
public class MenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String uid;

	@Column(name="name")
	private String name;
	
	@Column(name="pinyin")
	private String pinyin;

	@Column(name="parent_id")
	private BigInteger parentId;
	
	// 子类型
	private List<MenuEntity> childSubjects;

	public MenuEntity() {
	}

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigInteger getParentId() {
		return this.parentId;
	}

	public void setParentId(BigInteger parentId) {
		this.parentId = parentId;
	}

	public List<MenuEntity> getChildSubjects() {
		return childSubjects;
	}

	public void setChildSubjects(List<MenuEntity> childSubjects) {
		this.childSubjects = childSubjects;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

}