package com.tamguo.mybatis.handler;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.tamguo.mybatis.xmltags.ChooseNode;
import com.tamguo.mybatis.xmltags.ForeachNode;
import com.tamguo.mybatis.xmltags.IfNode;
import com.tamguo.mybatis.xmltags.SqlNode;
import com.tamguo.mybatis.xmltags.TextNode;
import com.tamguo.mybatis.xmltags.WhenNode;
import com.tamguo.mybatis.xmltags.WhereNode;


public class ConditionHandler extends XMLHandler {

	public ConditionHandler(Document doc, String id, Class<?> entity) {
		super(doc, id, entity);
	}

	private Element buildWhereElement() {
		Element whereElement = new WhereNode(getDoc()).build();
		whereElement.appendChild(buildIfElement());
		whereElement.appendChild(buildOrderByElement());
		return whereElement;
	}

	private Element buildIfElement() {
		Element ifElement = new IfNode(getDoc(), "criteria != null").build();
		ifElement.appendChild(buildForeachElement());
		return ifElement;
	}

	private Node buildOrderByElement() {
		Element ifElement = new IfNode(getDoc(), "orderByClause != null and orderByClause != ''").build();
		ifElement.appendChild(new TextNode(getDoc(), "order by ${orderByClause}").build());
		Element order = new IfNode(getDoc(), "order != null and order != ''").build();
		// DESC ASC
		order.appendChild(new TextNode(getDoc(), "${order}").build());
		ifElement.appendChild(order);
		return ifElement;
	}

	private Element buildForeachElement() {
		Element foreachElement = new ForeachNode(getDoc(), "criteria", "criterion", null, null, null, null).build();
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
		whenElement.appendChild(
				new TextNode(getDoc(), "and ${criterion.condition} concat('%',#{criterion.value},'%')").build());
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
		whenElement.appendChild(
				new TextNode(getDoc(), "and ${criterion.condition} #{criterion.value} and #{criterion.secondValue}")
						.build());
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

	@Override
	public Element build() {
		Element conditionElement = new SqlNode(getDoc(), getId()).build();
		conditionElement.appendChild(buildWhereElement());
		return conditionElement;
	}
}
