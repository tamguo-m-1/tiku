package com.tamguo.mybatis.xmltags;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class IncludeNode extends BaseNode {
	private String refid;

	public IncludeNode(Document doc, String refid) {
		super(doc);
		this.setRefid(refid);
	}

	public String getRefid() {
		return refid;
	}

	public void setRefid(String refid) {
		this.refid = refid;
	}

	@Override
	public Node build() {
		Element includeElement = getDoc().createElement("include");
		setAttribute(includeElement, "refid", getRefid());
		return includeElement;
	}
}
