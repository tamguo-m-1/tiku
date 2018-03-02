package com.tamguo.mybatis.xmltags;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class IdNode extends BaseNode {
	private String column;
	private String property;
	private String jdbcType;

	public IdNode(Document doc, String column, String property, String jdbyType) {
		super(doc);
		this.setColumn(column);
		this.setProperty(property);
		this.setJdbcType(jdbcType);
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
		Element idElement = getDoc().createElement("id");
		setAttribute(idElement, "column", getColumn());
		setAttribute(idElement, "property", getProperty());
		setAttribute(idElement, "jdbcType", getJdbcType());
		return idElement;
	}

}
