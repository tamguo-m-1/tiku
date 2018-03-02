package com.tamguo.mybatis.xmltags;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WhenNode extends BaseNode {
	private String expression;

	public WhenNode(Document doc, String expression) {
		super(doc);
		this.setExpression(expression);
	}

	@Override
	public Element build() {
		Element whenElement = getDoc().createElement("when");
		setAttribute(whenElement, "test", getExpression());
		return whenElement;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

}
