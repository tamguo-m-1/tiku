package com.tamguo.mybatis.xmltags;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ChooseNode extends BaseNode {

	public ChooseNode(Document doc) {
		super(doc);
	}

	@Override
	public Element build() {
		Element chooseElement = getDoc().createElement("choose");
		return chooseElement;
	}

}
