package com.tamguo.util;

import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.Page;

public class PageUtils {
    
	// 是否下一页按钮
	private Boolean isShowNextBtn = false;
	
	// 是否上一页按钮
	private Boolean isShowPreBtn = false;
	
	// 当前页
	private String currPageNum;
	
	// 页码列表
	private List<String> pageNums;
	
	// 总页数
	private String totalPage;
	
	// 总数量
	private String total;
	
	// 数据
	private List<?> list;
	
	public static PageUtils getPage(Page<?> page){
		PageUtils pg = new PageUtils();
		if(page.getStartRow() > 0){
			pg.setIsShowPreBtn(true);
		}
		if(page.getEndRow() < page.getTotal()){
			pg.setIsShowNextBtn(true);
		}
		List<String> pgNums = new ArrayList<>();
		if((page.getStartRow() + 1) > page.getPageSize()){
			if(page.getPages() > 10){
				pgNums.add("1");
				pgNums.add("2");
				pgNums.add("3");
				pgNums.add("...");
				if(page.getEndRow() == page.getTotal()){
					pgNums.add(((Integer)(page.getPageNum() - 2)).toString());
					pgNums.add(((Integer)(page.getPageNum() - 1)).toString());
					pgNums.add(((Integer)page.getPageNum()).toString());
				}else{
					pgNums.add(((Integer)(page.getPageNum() - 1)).toString());
					pgNums.add(((Integer)page.getPageNum()).toString());
					pgNums.add(((Integer)(page.getPageNum() + 1)).toString());
				}
			}else{
				Integer n = 1;
				if(page.getTotal() > 0){
					while(true){
						pgNums.add(n.toString());
						if(n  >= page.getPages()){
							break;
						}
						n ++;
					}
				}
			}
		} else {
			Integer n = 1;
			if(page.getTotal() > 0){
				while(true){
					pgNums.add(n.toString());
					if(n  >= page.getPages()){
						break;
					}
					n ++;
				}
			}
		}
		pg.setPageNums(pgNums);
		pg.setList(page.getResult());
		pg.setCurrPageNum(((Integer)page.getPageNum()).toString());
		pg.setTotal(((Long)page.getTotal()).toString());
		pg.setTotalPage(((Integer)page.getPages()).toString());
		return pg;
	}


	public Boolean getIsShowNextBtn() {
		return isShowNextBtn;
	}

	public void setIsShowNextBtn(Boolean isShowNextBtn) {
		this.isShowNextBtn = isShowNextBtn;
	}

	public List<String> getPageNums() {
		return pageNums;
	}

	public void setPageNums(List<String> pageNums) {
		this.pageNums = pageNums;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}


	public Boolean getIsShowPreBtn() {
		return isShowPreBtn;
	}


	public void setIsShowPreBtn(Boolean isShowPreBtn) {
		this.isShowPreBtn = isShowPreBtn;
	}


	public String getCurrPageNum() {
		return currPageNum;
	}


	public void setCurrPageNum(String currPageNum) {
		this.currPageNum = currPageNum;
	}


	public String getTotalPage() {
		return totalPage;
	}


	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}


	public String getTotal() {
		return total;
	}


	public void setTotal(String total) {
		this.total = total;
	}
}