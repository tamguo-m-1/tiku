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
	
	@Column(name="paper_id")
	private BigInteger paperId;

	@Column(name="answer")
	private String answer;

	@Column(name="chapter_id")
	private BigInteger chapterId;

	@Column(name="question_type")
	private String questionType;

	@Column(name="content")
	private String content;
	
	@Column(name="subject_id")
	private String subjectId;

	@Column(name="course_id")
	private String courseId;
	
	@Column(name="review_point")
	private String reviewPoint;
	
	@Column(name="year")
	private String year;
	
	@Column(name="score")
	private Integer score;
	
	private String courseName;
	
	private String chapterName;
	
	private String subjectName;

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
	
	public String getQuestionType() {
		return this.questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
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

	public BigInteger getPaperId() {
		return paperId;
	}

	public void setPaperId(BigInteger paperId) {
		this.paperId = paperId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

}