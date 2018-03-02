package com.tamguo.mybatis.handler;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.tamguo.mybatis.xmltags.DeleteNode;
import com.tamguo.mybatis.xmltags.IncludeNode;
import com.tamguo.mybatis.xmltags.TextNode;

public class DeleteByConditionHandler extends XMLHandler {
	private static final String DELETE = " delete from %s";

	public DeleteByConditionHandler(Document doc, String id, Class<?> entity) {
		super(doc, id, entity);
	}

	@Override
	public Element build() {
		Element deleteElement = new DeleteNode(getDoc(), getId()).build();
		Text text = new TextNode(getDoc(), String.format(DELETE, getTableName())).build();
		deleteElement.appendChild(text);
		deleteElement.appendChild(new IncludeNode(getDoc(), "condition").build());
		return deleteElement;
	}

}
