package com.tamguo.mybatis.handler;

import java.lang.reflect.Field;
import javax.persistence.Id;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.tamguo.mybatis.enums.BaseEnum;
import com.tamguo.mybatis.xmltags.IdNode;
import com.tamguo.mybatis.xmltags.ResultMapNode;
import com.tamguo.mybatis.xmltags.resultNode;

public class ResultMapHandler extends XMLHandler {

	public ResultMapHandler(Document doc, String id, Class<?> entity) {
		super(doc, id, entity);
	}

	public Element build() {
		Element resultMapElement = new ResultMapNode(getDoc(), getId(), getClazz().getName()).build();
		Field[] declaredFields = getClazz().getDeclaredFields();
		for (Field field : declaredFields) {
			if (isPersistence(field)) {
				if (field.isAnnotationPresent(Id.class)) {
					resultMapElement.appendChild(
							new IdNode(getDoc(), getColumnName(field), field.getName(), getJdbcType(field)).build());
				} else {
					if (BaseEnum.class.isAssignableFrom(field.getType())) {
						resultMapElement.appendChild(
								new resultNode(getDoc(), getColumnName(field), field.getName(), null, ENUM_HANDLER)
										.build());
					} else {
						resultMapElement.appendChild(
								new resultNode(getDoc(), getColumnName(field), field.getName(), getJdbcType(field))
										.build());
					}
				}
			}
		}
		return resultMapElement;
	}
}
