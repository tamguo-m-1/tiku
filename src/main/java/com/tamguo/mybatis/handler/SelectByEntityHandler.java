package com.tamguo.mybatis.handler;

import java.lang.reflect.Field;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.tamguo.mybatis.enums.BaseEnum;
import com.tamguo.mybatis.xmltags.IfNode;
import com.tamguo.mybatis.xmltags.IncludeNode;
import com.tamguo.mybatis.xmltags.SelectNode;
import com.tamguo.mybatis.xmltags.TextNode;
import com.tamguo.mybatis.xmltags.WhereNode;

public class SelectByEntityHandler extends XMLHandler {
	private String resultMap;
	private static final String FROM = " from %s ";
	private static final String AND = "and %s = #{%s,jdbcType=%s}";
	private static final String AND_TYPEHANDLER = "and %s = #{%s,typeHandler=%s}";

	public SelectByEntityHandler(Document doc, String id, Class<?> entity, String resultMap) {
		super(doc, id, entity);
		this.setResultMap(resultMap);
	}

	@Override
	public Element build() {
		Element selectByEntityElement = new SelectNode(getDoc(), getId(), getResultMap()).build();
		selectByEntityElement.appendChild(new TextNode(getDoc(), "select").build());
		selectByEntityElement.appendChild(new IncludeNode(getDoc(), "column").build());
		selectByEntityElement.appendChild(new TextNode(getDoc(), String.format(FROM, getTableName())).build());
		Element whereElement = new WhereNode(getDoc()).build();
		for (Field field : getClazz().getDeclaredFields()) {
			if (isPersistence(field)) {
				Element ifElement = new IfNode(getDoc(), getExpression(field)).build();
				Text text = null;
				if (BaseEnum.class.isAssignableFrom(field.getType())) {
					text = new TextNode(getDoc(),
							String.format(AND_TYPEHANDLER, getColumnName(field), field.getName(), ENUM_HANDLER))
									.build();
				} else {
					text = new TextNode(getDoc(),
							String.format(AND, getColumnName(field), field.getName(), getJdbcType(field))).build();
				}
				ifElement.appendChild(text);
				whereElement.appendChild(ifElement);
			}
		}
		selectByEntityElement.appendChild(whereElement);
		return selectByEntityElement;
	}

	public String getResultMap() {
		return resultMap;
	}

	public void setResultMap(String resultMap) {
		this.resultMap = resultMap;
	}

}
