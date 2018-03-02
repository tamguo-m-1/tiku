package com.tamguo.mybatis.utils;

import java.util.List;

public class Page<T> {
	public static final int DEFAULT_PAGESIZE = 10;
	private int pageNo; // 当前页码
	private int pageSize = DEFAULT_PAGESIZE; // 每页行数
	private int totalRecord = 0; // 总记录数
	private int totalPage = 0; // 总页数
	private Condition<T> condition; // 查询条件
	private List<T> result; // 查询结果

	public Page() {
		this.pageNo = 1;
		this.pageSize = DEFAULT_PAGESIZE;
		this.totalRecord = 0;
		this.totalPage = 0;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public Condition<T> getCondition() {
		return condition;
	}

	public void setCondition(Condition<T> condition) {
		this.condition = condition;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(512);
		sb.append(" pageNo:").append(getPageNo()).append(",");
		sb.append(" pageSize:").append(getPageSize()).append(",");
		sb.append(" totalRecord:").append(getTotalRecord()).append(",");
		sb.append(" totalPage:").append(getTotalPage()).append(",");
		sb.append(" resultSize:").append(getResult() == null ? " result is null" : getResult().size()).append(",");
		return sb.toString();
	}
}
