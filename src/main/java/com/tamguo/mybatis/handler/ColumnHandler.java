package com.tamguo.mybatis.handler;

import java.lang.reflect.Field;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.tamguo.mybatis.xmltags.SqlNode;
import com.tamguo.mybatis.xmltags.TextNode;



public class ColumnHandler extends XMLHandler {

	public ColumnHandler(Document doc, String id, Class<?> entity) {
		super(doc, id, entity);
	}

	public Element build() {
		Element sqlElement = new SqlNode(getDoc(), getId()).build();
		Field[] declaredFields = getClazz().getDeclaredFields();
		StringBuilder sb = new StringBuilder(512);
		for (Field field : declaredFields) {
			if (isPersistence(field)) {
				sb.append("`").append(getColumnName(field)).append("`,");
			}
		}
		String columnText = sb.deleteCharAt(sb.lastIndexOf(",")).toString();
		sqlElement.appendChild(new TextNode(getDoc(), columnText).build());
		return sqlElement;
	}
}
