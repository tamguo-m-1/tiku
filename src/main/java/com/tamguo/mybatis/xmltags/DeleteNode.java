package com.tamguo.mybatis.xmltags;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DeleteNode extends BaseNode {
	private String id;

	public DeleteNode(Document doc, String id) {
		super(doc);
		this.setId(id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public Element build() {
		Element deleteElement = getDoc().createElement("delete");
		setAttribute(deleteElement, "id", getId());
		return deleteElement;
	}

}
