package com.tamguo.mybatis.handler;

import java.lang.reflect.Field;
import javax.persistence.GeneratedValue;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.tamguo.mybatis.enums.BaseEnum;
import com.tamguo.mybatis.xmltags.IfNode;
import com.tamguo.mybatis.xmltags.InsertNode;
import com.tamguo.mybatis.xmltags.TextNode;
import com.tamguo.mybatis.xmltags.TrimNode;

public class InsertHandler extends XMLHandler {
	private static final String INSERT = "insert into %s";
	private static final String VALUE = " #{%s,jdbcType=%s}, ";
	private static final String VALUE_TYPEHANDLER = " #{%s,typeHandler=%s}, ";

	public InsertHandler(Document doc, String id, Class<?> entity) {
		super(doc, id, entity);
	}

	@Override
	public Element build() {
		Field idField = getIdField();
		Element insertElement = null;
		// is need autoincrement key
		if (idField.isAnnotationPresent(GeneratedValue.class)) {
			insertElement = new InsertNode(getDoc(), getId(), true, idField.getName()).build();
		} else {
			insertElement = new InsertNode(getDoc(), getId(), false, null).build();
		}
		insertElement.appendChild(new TextNode(getDoc(), String.format(INSERT, getTableName())).build());
		Element colTrim = new TrimNode(getDoc(), "(", ")", ",").build();
		Element valTrim = new TrimNode(getDoc(), "(", ")", ",").build();
		for (Field field : getClazz().getDeclaredFields()) {
			if (!field.isAnnotationPresent(GeneratedValue.class) && isPersistence(field)) {
				String expression = getExpression(field);
				// col
				Element ifElementCol = new IfNode(getDoc(), expression).build();
				ifElementCol.appendChild(new TextNode(getDoc(),
						new StringBuilder().append("`").append(getColumnName(field)).append("`,").toString()).build());
				colTrim.appendChild(ifElementCol);
				// value
				Element ifElementVal = new IfNode(getDoc(), expression).build();
				if (BaseEnum.class.isAssignableFrom(field.getType())) {
					ifElementVal.appendChild(
							new TextNode(getDoc(), String.format(VALUE_TYPEHANDLER, field.getName(), ENUM_HANDLER))
									.build());
				} else {
					ifElementVal.appendChild(
							new TextNode(getDoc(), String.format(VALUE, field.getName(), getJdbcType(field))).build());
				}
				valTrim.appendChild(ifElementVal);
			}
		}
		insertElement.appendChild(colTrim);
		insertElement.appendChild(new TextNode(getDoc(), "values").build());
		insertElement.appendChild(valTrim);
		return insertElement;
	}
}
