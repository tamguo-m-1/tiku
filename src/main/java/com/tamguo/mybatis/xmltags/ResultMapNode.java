package com.tamguo.mybatis.xmltags;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ResultMapNode extends BaseNode {
	private String id;
	private String type;

	public ResultMapNode(Document doc, String id, String type) {
		super(doc);
		this.setId(id);
		this.setType(type);
	}

	@Override
	public Element build() {
		Element resultMapElement = getDoc().createElement("resultMap");
		setAttribute(resultMapElement, "id", getId());
		setAttribute(resultMapElement, "type", getType());
		return resultMapElement;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
