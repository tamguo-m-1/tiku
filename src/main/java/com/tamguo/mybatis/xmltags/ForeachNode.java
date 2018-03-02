package com.tamguo.mybatis.xmltags;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ForeachNode extends BaseNode {
	private String collection;
	private String item;
	private String separator;
	private String open;
	private String close;
	private String index;

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getClose() {
		return close;
	}

	public void setClose(String close) {
		this.close = close;
	}

	public ForeachNode(Document doc, String collection, String item, String open, String close, String separator, String index) {
		super(doc);
		this.setClose(close);
		this.setOpen(open);
		this.setCollection(collection);
		this.setItem(item);
		this.setSeparator(separator);
		this.setIndex(index);
	}

	@Override
	public Element build() {
		Element foreachElement = getDoc().createElement("foreach");
		setAttribute(foreachElement, "collection", getCollection());
		setAttribute(foreachElement, "item", getItem());
		setAttribute(foreachElement, "open", getOpen());
		setAttribute(foreachElement, "close", getClose());
		setAttribute(foreachElement, "separator", getSeparator());
		setAttribute(foreachElement, "index", getIndex());
		return foreachElement;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

}
