package com.tamguo.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the tiku_chapter database table.
 * 
 */
@Entity
@Table(name="tiku_chapter")
@NamedQuery(name="ChapterEntity.findAll", query="SELECT c FROM ChapterEntity c")
public class ChapterEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private String uid;

	@Column(name="course_id")
	private String courseId;

	@Column(name="name")
	private String name;

	@Column(name="parent_id")
	private String parentId;
	
	@Column(name="question_num")
	private Integer questionNum;
	
	@Column(name="point_num")
	private Integer pointNum;
	
	@Column(name="orders")
	private Integer orders;
	
	private List<ChapterEntity> childChapterList;

	public ChapterEntity() {
	}

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getCourseId() {
		return this.courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<ChapterEntity> getChildChapterList() {
		return childChapterList;
	}

	public void setChildChapterList(List<ChapterEntity> childChapterList) {
		this.childChapterList = childChapterList;
	}

	public Integer getQuestionNum() {
		return questionNum;
	}

	public void setQuestionNum(Integer questionNum) {
		this.questionNum = questionNum;
	}

	public Integer getPointNum() {
		return pointNum;
	}

	public void setPointNum(Integer pointNum) {
		this.pointNum = pointNum;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

}