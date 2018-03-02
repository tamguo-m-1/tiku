package com.tamguo.mybatis.xmltags;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WhereNode extends BaseNode {

	public WhereNode(Document doc) {
		super(doc);
	}

	@Override
	public Element build() {
		Element whereElement = getDoc().createElement("where");
		return whereElement;
	}
}
