package com.tamguo.mybatis.xmltags;

import org.w3c.dom.Document;
import org.w3c.dom.Text;

public class TextNode extends BaseNode {
	private String text;

	public TextNode(Document doc, String text) {
		super(doc);
		this.setText(text);
	}

	@Override
	public Text build() {
		Text textNode = getDoc().createTextNode(new StringBuilder(" ").append(getText()).append(" ").toString());
		return textNode;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
