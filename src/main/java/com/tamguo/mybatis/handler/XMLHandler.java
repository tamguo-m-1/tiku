package com.tamguo.mybatis.handler;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import com.tamguo.mybatis.EnumTypeHandler;

public abstract class XMLHandler {
	protected static final String ENUM_HANDLER = EnumTypeHandler.class.getName();
	private static final String TEST = "%s != null";
	private static final String TEST_STR = "%s != null and %s != ''";
	private String id;
	private Class<?> clazz;
	private Document doc;
	private String tableName;
	private Field idField;

	public XMLHandler(Document doc, String id, Class<?> entity) {
		this.setId(id);
		this.setDoc(doc);
		this.setClazz(entity);
		this.setTableName(initTableName());
		this.setIdField(initIdField());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Field getIdField() {
		return idField;
	}

	public void setIdField(Field idField) {
		this.idField = idField;
	}

	public boolean isPersistence(Field field) {
		if (field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(Id.class)) {
			return true;
		}
		return false;
	}

	public String getColumnName(Field field) {
		String columnName = field.getName();
		if (field.isAnnotationPresent(Column.class)) {
			String name = field.getAnnotation(Column.class).name();
			if (!StringUtils.isEmpty(name)) {
				columnName = name;
			}
		}
		return columnName;
	}

	public String getJdbcType(Field field) {
		Class<?> type = field.getType();
		if (type == BigDecimal.class) {
			return "DECIMAL";
		} else if (type == Long.class) {
			return "BIGINT";
		} else if (type == Integer.class) {
			return "INTEGER";
		} else if (type == Date.class) {
			return "TIMESTAMP";
		} else {
			return "VARCHAR";
		}
	}

	private String initTableName() {
		if (getClazz().isAnnotationPresent(Table.class)) {
			return getClazz().getAnnotation(Table.class).name();
		}
		return null;
	}

	private Field initIdField() {
		Field[] declaredFields = getClazz().getDeclaredFields();
		for (Field field : declaredFields) {
			if (field.isAnnotationPresent(Id.class)) {
				return field;
			}
		}
		return null;
	}

	public String getExpression(Field field) {
		if (field.getType() == String.class) {
			return String.format(TEST_STR, field.getName(), field.getName());
		} else {
			return String.format(TEST, field.getName());
		}
	}

	public abstract Element build();
}
