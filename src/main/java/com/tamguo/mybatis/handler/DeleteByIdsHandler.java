package com.tamguo.mybatis.handler;

import java.lang.reflect.Field;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.tamguo.mybatis.xmltags.DeleteNode;
import com.tamguo.mybatis.xmltags.ForeachNode;
import com.tamguo.mybatis.xmltags.TextNode;

public class DeleteByIdsHandler extends XMLHandler {
	private static final String DELETE = "delete from %s where %s in";

	public DeleteByIdsHandler(Document doc, String id, Class<?> entity) {
		super(doc, id, entity);
	}

	@Override
	public Element build() {
		Field idField = getIdField();
		Element deleteByIdsElement = new DeleteNode(getDoc(), getId()).build();
		deleteByIdsElement.appendChild(new TextNode(getDoc(), String.format(DELETE, getTableName(), getColumnName(idField))).build());
		Element foreachElement = new ForeachNode(getDoc(), "list", "id", "(", ")", ",", null).build();
		foreachElement.appendChild(new TextNode(getDoc(), "#{id}").build());
		deleteByIdsElement.appendChild(foreachElement);
		return deleteByIdsElement;
	}

}
