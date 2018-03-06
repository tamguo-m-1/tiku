package com.tamguo.model;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;


/**
 * The persistent class for the tiku_chapter database table.
 * 
 */
@Entity
@Table(name="tiku_paper")
@NamedQuery(name="PaperEntity.findAll", query="SELECT c FROM PaperEntity c")
public class PaperEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String uid;

	@Column(name="course_id")
	private String courseId;
	
	@Column(name="school_id")
	private String schoolId;
	
	@Column(name="area_id")
	private String areaId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="question_info")
	private String questionInfo;
	
	@Column(name="type")
	private String type;
	
	@Column(name="year")
	private String year;
	
	@Column(name="down_hits")
	private Integer downHits;
	
	@Column(name="open_hits")
	private Integer openHits;
	
	public JSONArray getQueInfo(){
		if(StringUtils.isEmpty(getQuestionInfo())){
			return null;
		}
		return JSONArray.parseArray(getQuestionInfo());
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getDownHits() {
		return downHits;
	}

	public void setDownHits(Integer downHits) {
		this.downHits = downHits;
	}

	public Integer getOpenHits() {
		return openHits;
	}

	public void setOpenHits(Integer openHits) {
		this.openHits = openHits;
	}

	public String getQuestionInfo() {
		return questionInfo;
	}

	public void setQuestionInfo(String questionInfo) {
		this.questionInfo = questionInfo;
	}

}