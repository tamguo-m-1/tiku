package com.tamguo.mybatis.xmltags;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class IfNode extends BaseNode {
	private String expression;

	public IfNode(Document doc, String expression) {
		super(doc);
		this.setExpression(expression);
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public Element build() {
		Element ifElement = getDoc().createElement("if");
		setAttribute(ifElement, "test", getExpression());
		return ifElement;
	}
}
