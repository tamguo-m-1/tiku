package com.tamguo.mybatis.xmltags;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class resultNode extends BaseNode {
	private String column;
	private String property;
	private String jdbcType;
	private String typeHandler;

	public resultNode(Document doc, String column, String property, String jdbcType) {
		this(doc, column, property, jdbcType, null);
	}

	public resultNode(Document doc, String column, String property, String jdbyType, String typeHandler) {
		super(doc);
		this.setColumn(column);
		this.setProperty(property);
		this.setJdbcType(jdbcType);
		this.setTypeHandler(typeHandler);
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}

	@Override
	public Element build() {
		Element resultElement = getDoc().createElement("result");
		setAttribute(resultElement, "column", getColumn());
		setAttribute(resultElement, "property", getProperty());
		setAttribute(resultElement, "jdbcType", getJdbcType());
		setAttribute(resultElement, "typeHandler", getTypeHandler());
		return resultElement;
	}

	public String getTypeHandler() {
		return typeHandler;
	}

	public void setTypeHandler(String typeHandler) {
		this.typeHandler = typeHandler;
	}
}
