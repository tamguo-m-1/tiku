package com.tamguo.mybatis.handler;

import java.lang.reflect.Field;
import javax.persistence.GeneratedValue;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.tamguo.mybatis.xmltags.ChooseNode;
import com.tamguo.mybatis.xmltags.ForeachNode;
import com.tamguo.mybatis.xmltags.IfNode;
import com.tamguo.mybatis.xmltags.SetNode;
import com.tamguo.mybatis.xmltags.TextNode;
import com.tamguo.mybatis.xmltags.UpdateNode;
import com.tamguo.mybatis.xmltags.WhenNode;
import com.tamguo.mybatis.xmltags.WhereNode;

public class UpdateByConditionHandler extends XMLHandler {
	private static final String UPDATE = "update %s";
	private static final String VALUE = "%s = #{t.%s,jdbcType=%s}, ";
	private static final String IF_CONDITION = "t.%s != null";

	public UpdateByConditionHandler(Document doc, String id, Class<?> entity) {
		super(doc, id, entity);
	}

	@Override
	public Element build() {
		Element updateByConditionElement = new UpdateNode(getDoc(), getId()).build();
		updateByConditionElement.appendChild(new TextNode(getDoc(), String.format(UPDATE, getTableName())).build());
		Element setElement = new SetNode(getDoc()).build();
		for (Field field : getClazz().getDeclaredFields()) {
			if (isPersistence(field) && !field.isAnnotationPresent(GeneratedValue.class)) {
				Element ifNode = new IfNode(getDoc(), String.format(IF_CONDITION, field.getName())).build();
				ifNode.appendChild(new TextNode(getDoc(), String.format(VALUE, getColumnName(field), field.getName(), getJdbcType(field))).build());
				setElement.appendChild(ifNode);
			}
		}
		updateByConditionElement.appendChild(setElement);
		updateByConditionElement.appendChild(buildWhereElement());
		return updateByConditionElement;
	}

	private Element buildWhereElement() {
		Element whereElement = new WhereNode(getDoc()).build();
		whereElement.appendChild(buildIfElement());
		whereElement.appendChild(buildOrderByElement());
		return whereElement;
	}

	private Element buildIfElement() {
		Element ifElement = new IfNode(getDoc(), "c.criteria != null").build();
		ifElement.appendChild(buildForeachElement());
		return ifElement;
	}

	private Node buildOrderByElement() {
		Element ifElement = new IfNode(getDoc(), "c.orderByClause != null and c.orderByClause != ''").build();
		ifElement.appendChild(new TextNode(getDoc(), "order by ${c.orderByClause}").build());
		Element order = new IfNode(getDoc(), "c.order != null and c.order != ''").build();
		order.appendChild(new TextNode(getDoc(), "${c.order}").build());
		ifElement.appendChild(order);
		return ifElement;
	}

	private Element buildForeachElement() {
		Element foreachElement = new ForeachNode(getDoc(), "c.criteria", "criterion", null, null, null, null).build();
		foreachElement.appendChild(buildChooseElement());
		return foreachElement;
	}

	private Element buildChooseElement() {
		Element chooseElement = new ChooseNode(getDoc()).build();
		chooseElement.appendChild(buildWhenNoValue());
		chooseElement.appendChild(buildWhenSingleValue());
		chooseElement.appendChild(buildWhenBetweenValue());
		chooseElement.appendChild(buildWhenListValue());
		chooseElement.appendChild(buildWhenLikeValue());
		return chooseElement;
	}

	private Node buildWhenLikeValue() {
		Element whenElement = new WhenNode(getDoc(), "criterion.likeValue").build();
		whenElement.appendChild(new TextNode(getDoc(), "and ${criterion.condition} concat('%',#{criterion.value},'%')").build());
		return whenElement;
	}

	private Node buildWhenListValue() {
		Element whenElement = new WhenNode(getDoc(), "criterion.listValue").build();
		whenElement.appendChild(new TextNode(getDoc(), "and ${criterion.condition}").build());
		Element foreachElement = new ForeachNode(getDoc(), "criterion.value", "listItem", "(", ")", ",", null).build();
		foreachElement.appendChild(new TextNode(getDoc(), "#{listItem}").build());
		whenElement.appendChild(foreachElement);
		return whenElement;
	}

	private Node buildWhenBetweenValue() {
		Element whenElement = new WhenNode(getDoc(), "criterion.betweenValue").build();
		whenElement.appendChild(new TextNode(getDoc(), "and ${criterion.condition} #{criterion.value} and #{criterion.secondValue}").build());
		return whenElement;
	}

	private Node buildWhenSingleValue() {
		Element whenElement = new WhenNode(getDoc(), "criterion.singleValue").build();
		whenElement.appendChild(new TextNode(getDoc(), "and ${criterion.condition} #{criterion.value}").build());
		return whenElement;
	}

	private Node buildWhenNoValue() {
		Element whenElement = new WhenNode(getDoc(), "criterion.noValue").build();
		whenElement.appendChild(new TextNode(getDoc(), "and ${criterion.condition}").build());
		return whenElement;
	}

}
