package com.tamguo.mybatis.xmltags;

import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public abstract class BaseNode {
	private Document doc;

	public BaseNode(Document doc) {
		this.setDoc(doc);
	}

	public abstract Node build();

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public void setAttribute(Element element, String attribute, String value) {
		if (!StringUtils.isEmpty(value)) {
			element.setAttribute(attribute, value);
		}
	}

}
