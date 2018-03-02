package com.tamguo.mybatis.handler;

import java.lang.reflect.Field;
import javax.persistence.GeneratedValue;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.tamguo.mybatis.enums.BaseEnum;
import com.tamguo.mybatis.xmltags.IfNode;
import com.tamguo.mybatis.xmltags.SetNode;
import com.tamguo.mybatis.xmltags.TextNode;
import com.tamguo.mybatis.xmltags.UpdateNode;

public class UpdateHandler extends XMLHandler {
	private static final String UPDATE = "update %s";
	private static final String VALUE = "%s = #{%s,jdbcType=%s}, ";
	private static final String VALUE_ENUM_HANDLER = "%s = #{%s,typeHandler=%s}, ";
	private static final String WHERE = "where %s = #{%s,jdbcType=%s}";

	public UpdateHandler(Document doc, String id, Class<?> entity) {
		super(doc, id, entity);
	}

	@Override
	public Element build() {
		Element updateElement = new UpdateNode(getDoc(), getId()).build();
		updateElement.appendChild(new TextNode(getDoc(), String.format(UPDATE, getTableName())).build());
		Element setElement = new SetNode(getDoc()).build();
		for (Field field : getClazz().getDeclaredFields()) {
			if (isPersistence(field) && !field.isAnnotationPresent(GeneratedValue.class)) {
				Element ifNode = new IfNode(getDoc(), getExpression(field)).build();
				if (BaseEnum.class.isAssignableFrom(field.getType())) {
					ifNode.appendChild(new TextNode(getDoc(),
							String.format(VALUE_ENUM_HANDLER, getColumnName(field), field.getName(), ENUM_HANDLER))
									.build());
				} else {
					ifNode.appendChild(new TextNode(getDoc(),
							String.format(VALUE, getColumnName(field), field.getName(), getJdbcType(field))).build());
				}
				setElement.appendChild(ifNode);
			}
		}
		updateElement.appendChild(setElement);
		Field idField = getIdField();
		updateElement.appendChild(new TextNode(getDoc(),
				String.format(WHERE, getColumnName(idField), idField.getName(), getJdbcType(idField))).build());
		return updateElement;
	}

}
