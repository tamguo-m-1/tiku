package com.tamguo.mybatis.xmltags;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SetNode extends BaseNode {

	public SetNode(Document doc) {
		super(doc);
	}

	@Override
	public Element build() {
		Element setElement = getDoc().createElement("set");
		return setElement;
	}

}
