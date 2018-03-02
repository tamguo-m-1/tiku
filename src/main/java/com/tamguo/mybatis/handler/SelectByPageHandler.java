package com.tamguo.mybatis.handler;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.tamguo.mybatis.xmltags.ChooseNode;
import com.tamguo.mybatis.xmltags.ForeachNode;
import com.tamguo.mybatis.xmltags.IfNode;
import com.tamguo.mybatis.xmltags.IncludeNode;
import com.tamguo.mybatis.xmltags.SelectNode;
import com.tamguo.mybatis.xmltags.TextNode;
import com.tamguo.mybatis.xmltags.WhenNode;
import com.tamguo.mybatis.xmltags.WhereNode;

public class SelectByPageHandler extends XMLHandler {
	private String resultMap;
	private static final String FROM = " from %s ";

	public SelectByPageHandler(Document doc, String id, Class<?> entity, String resultMap) {
		super(doc, id, entity);
		this.setResultMap(resultMap);
	}

	@Override
	public Element build() {
		Element selectByPageElement = new SelectNode(getDoc(), getId(), getResultMap()).build();
		selectByPageElement.appendChild(new TextNode(getDoc(), "select").build());
		selectByPageElement.appendChild(new IncludeNode(getDoc(), "column").build());
		selectByPageElement.appendChild(new TextNode(getDoc(), String.format(FROM, getTableName())).build());
		selectByPageElement.appendChild(buildWhereElement());
		return selectByPageElement;
	}

	private Element buildWhereElement() {
		Element whereElement = new WhereNode(getDoc()).build();
		whereElement.appendChild(buildIfElement());
		return whereElement;
	}

	private Node buildOrderByElement() {
		Element ifElement = new IfNode(getDoc(), "condition.orderByClause != null and condition.orderByClause != ''").build();
		ifElement.appendChild(new TextNode(getDoc(), "order by ${condition.orderByClause}").build());
		Element order = new IfNode(getDoc(), "condition.order != null and condition.order != ''").build();
		order.appendChild(new TextNode(getDoc(), "${condition.order}").build());
		ifElement.appendChild(order);
		return ifElement;
	}

	private Element buildIfElement() {
		Element ifElement = new IfNode(getDoc(), "condition != null").build();
		ifElement.appendChild(buildForeachElement());
		ifElement.appendChild(buildOrderByElement());
		return ifElement;
	}

	private Element buildForeachElement() {
		Element foreachElement = new ForeachNode(getDoc(), "condition.criteria", "criterion", null, null, null, null).build();
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
		foreachElement.appendChild(new TextNode(getDoc(), "{listItem}").build());
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

	public String getResultMap() {
		return resultMap;
	}

	public void setResultMap(String resultMap) {
		this.resultMap = resultMap;
	}

}
