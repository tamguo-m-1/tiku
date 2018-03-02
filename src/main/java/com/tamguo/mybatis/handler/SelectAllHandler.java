package com.tamguo.mybatis.handler;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.tamguo.mybatis.xmltags.IncludeNode;
import com.tamguo.mybatis.xmltags.SelectNode;
import com.tamguo.mybatis.xmltags.TextNode;

public class SelectAllHandler extends XMLHandler {
	private String resultMap;
	private static final String SELECT_ALL = "from %s";

	public SelectAllHandler(Document doc, String id, Class<?> entity, String resultMap) {
		super(doc, id, entity);
		this.setResultMap(resultMap);
	}

	@Override
	public Element build() {
		Element selectAllElement = new SelectNode(getDoc(), getId(), getResultMap()).build();
		selectAllElement.appendChild(new TextNode(getDoc(), "select").build());
		selectAllElement.appendChild(new IncludeNode(getDoc(), "column").build());
		selectAllElement.appendChild(new TextNode(getDoc(), String.format(SELECT_ALL, getTableName())).build());
		return selectAllElement;
	}

	public String getResultMap() {
		return resultMap;
	}

	public void setResultMap(String resultMap) {
		this.resultMap = resultMap;
	}

}
