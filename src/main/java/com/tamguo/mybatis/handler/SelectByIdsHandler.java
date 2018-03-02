package com.tamguo.mybatis.handler;

import java.lang.reflect.Field;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.tamguo.mybatis.xmltags.ForeachNode;
import com.tamguo.mybatis.xmltags.IncludeNode;
import com.tamguo.mybatis.xmltags.SelectNode;
import com.tamguo.mybatis.xmltags.TextNode;

public class SelectByIdsHandler extends XMLHandler {
	private String resultMap;
	private static final String FROM = "from %s where %s in";

	public SelectByIdsHandler(Document doc, String id, Class<?> entity, String resultMap) {
		super(doc, id, entity);
		this.setResultMap(resultMap);
	}

	@Override
	public Element build() {
		Field idField = getIdField();
		Element selectByIdsElement = new SelectNode(getDoc(), getId(), getResultMap()).build();
		selectByIdsElement.appendChild(new TextNode(getDoc(), "select").build());
		selectByIdsElement.appendChild(new IncludeNode(getDoc(), "column").build());
		selectByIdsElement.appendChild(new TextNode(getDoc(), String.format(FROM, getTableName(), getColumnName(idField))).build());
		Element foreachElement = new ForeachNode(getDoc(), "list", "id", "(", ")", ",", null).build();
		foreachElement.appendChild(new TextNode(getDoc(), "#{id}").build());
		selectByIdsElement.appendChild(foreachElement);
		return selectByIdsElement;
	}

	public String getResultMap() {
		return resultMap;
	}

	public void setResultMap(String resultMap) {
		this.resultMap = resultMap;
	}
}
