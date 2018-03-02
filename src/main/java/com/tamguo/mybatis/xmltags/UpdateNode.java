package com.tamguo.mybatis.xmltags;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class UpdateNode extends BaseNode {
	private String id;

	public UpdateNode(Document doc, String id) {
		super(doc);
		this.setId(id);
	}

	@Override
	public Element build() {
		Element updateElement = getDoc().createElement("update");
		setAttribute(updateElement, "id", getId());
		return updateElement;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
