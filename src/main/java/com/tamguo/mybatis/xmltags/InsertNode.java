package com.tamguo.mybatis.xmltags;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class InsertNode extends BaseNode {
	private boolean isAutoIncrementKey;
	private String keyProperty;
	private String id;

	public InsertNode(Document doc, String id, boolean isAutoIncrementKey, String keyProperty) {
		super(doc);
		this.setId(id);
		this.setAutoIncrementKey(isAutoIncrementKey);
		this.setKeyProperty(keyProperty);
	}

	@Override
	public Element build() {
		Element insertElement = getDoc().createElement("insert");
		if (isAutoIncrementKey()) {
			setAttribute(insertElement, "useGeneratedKeys", "true");
			setAttribute(insertElement, "keyProperty", getKeyProperty());
		}
		setAttribute(insertElement, "id", getId());
		return insertElement;
	}

	public boolean isAutoIncrementKey() {
		return isAutoIncrementKey;
	}

	public void setAutoIncrementKey(boolean isAutoIncrementKey) {
		this.isAutoIncrementKey = isAutoIncrementKey;
	}

	public String getKeyProperty() {
		return keyProperty;
	}

	public void setKeyProperty(String keyProperty) {
		this.keyProperty = keyProperty;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
