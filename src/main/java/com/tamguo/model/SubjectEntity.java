package com.tamguo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="tiku_subject")
@NamedQuery(name="SubjectEntity.findAll", query="SELECT c FROM SubjectEntity c")
public class SubjectEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String uid;

	@Column(name="name")
	private String name;
	
	@Column(name="course_id")
	private String courseId;

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

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
}
