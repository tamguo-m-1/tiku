package com.tamguo.mybatis.xmltags;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SqlNode extends BaseNode {
	private String id;

	public SqlNode(Document doc, String id) {
		super(doc);
		this.setId(id);
	}

	@Override
	public Element build() {
		Element sqlElement = getDoc().createElement("sql");
		setAttribute(sqlElement, "id", getId());
		return sqlElement;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
