package com.tamguo.mybatis.handler;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.tamguo.mybatis.xmltags.DeleteNode;
import com.tamguo.mybatis.xmltags.TextNode;

public class DeleteHandler extends XMLHandler {
	private static final String DELETE = " delete from %s where %s = #{%s,jdbcType=%s} ";

	public DeleteHandler(Document doc, String id, Class<?> entity) {
		super(doc, id, entity);
	}

	@Override
	public Element build() {
		Element deleteElement = new DeleteNode(getDoc(), getId()).build();
		Text text = new TextNode(getDoc(), String.format(DELETE, getTableName(), getColumnName(getIdField()), getIdField().getName(), getJdbcType(getIdField()))).build();
		deleteElement.appendChild(text);
		return deleteElement;
	}

}
