package com.tamguo.mybatis.handler;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import com.tamguo.mybatis.xmltags.IncludeNode;
import com.tamguo.mybatis.xmltags.SelectNode;
import com.tamguo.mybatis.xmltags.TextNode;

public class SelectByConditionHandler extends XMLHandler {
	private String resultMap;
	private static final String FROM = " from %s ";

	public SelectByConditionHandler(Document doc, String id, Class<?> entity, String resultMap) {
		super(doc, id, entity);
		this.setResultMap(resultMap);
	}

	@Override
	public Element build() {
		Element selectByConditionElement = new SelectNode(getDoc(), getId(), getResultMap()).build();
		selectByConditionElement.appendChild(new TextNode(getDoc(), "select").build());
		selectByConditionElement.appendChild(new IncludeNode(getDoc(), "column").build());
		selectByConditionElement.appendChild(new TextNode(getDoc(), String.format(FROM, getTableName())).build());
		selectByConditionElement.appendChild(new IncludeNode(getDoc(), "condition").build());
		return selectByConditionElement;
	}

	public String getResultMap() {
		return resultMap;
	}

	public void setResultMap(String resultMap) {
		this.resultMap = resultMap;
	}

}
