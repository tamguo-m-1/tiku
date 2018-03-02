package com.tamguo.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the tiku_question database table.
 * 
 */
@Entity
@Table(name="tiku_question")
@NamedQuery(name="QuestionEntity.findAll", query="SELECT q FROM QuestionEntity q")
public class QuestionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String uid;

	@Column(name="analysis")
	private String analysis;

	@Column(name="answer")
	private String answer;

	@Column(name="chapter_id")
	private BigInteger chapterId;

	@Column(name="fallibility")
	private String fallibility;

	@Column(name="question_type")
	private String questionType;

	@Column(name="content")
	private String content;

	@Column(name="subject_id")
	private BigInteger subjectId;
	
	@Column(name="review_point")
	private String reviewPoint;
	
	@Column(name="year")
	private String year;
	
	@Column(name="score")
	private Integer score;

	public QuestionEntity() {
	}

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getAnalysis() {
		return this.analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public BigInteger getChapterId() {
		return this.chapterId;
	}

	public void setChapterId(BigInteger chapterId) {
		this.chapterId = chapterId;
	}

	public String getFallibility() {
		return this.fallibility;
	}

	public void setFallibility(String fallibility) {
		this.fallibility = fallibility;
	}

	public String getQuestionType() {
		return this.questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public BigInteger getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(BigInteger subjectId) {
		this.subjectId = subjectId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReviewPoint() {
		return reviewPoint;
	}

	public void setReviewPoint(String reviewPoint) {
		this.reviewPoint = reviewPoint;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}