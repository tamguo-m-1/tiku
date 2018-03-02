package com.tamguo.mybatis.handler;

import java.lang.reflect.Field;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.tamguo.mybatis.xmltags.IncludeNode;
import com.tamguo.mybatis.xmltags.SelectNode;
import com.tamguo.mybatis.xmltags.TextNode;

public class SelectHandler extends XMLHandler {
	private String resultMap;
	private static final String SELECT = " from %s where %s = #{%s,jdbcType=%s} ";

	public SelectHandler(Document doc, String id, Class<?> entity, String resultMap) {
		super(doc, id, entity);
		this.setResultMap(resultMap);
	}

	@Override
	public Element build() {
		Field idField = getIdField();
		Element selectElement = new SelectNode(getDoc(), getId(), getResultMap()).build();
		selectElement.appendChild(new TextNode(getDoc(), "select").build());
		selectElement.appendChild(new IncludeNode(getDoc(), "column").build());
		Text text = new TextNode(getDoc(), String.format(SELECT, getTableName(), getColumnName(idField), idField.getName(), getJdbcType(idField))).build();
		selectElement.appendChild(text);
		return selectElement;
	}

	public String getResultMap() {
		return resultMap;
	}

	public void setResultMap(String resultMap) {
		this.resultMap = resultMap;
	}

}
