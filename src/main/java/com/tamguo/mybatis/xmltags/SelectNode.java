package com.tamguo.mybatis.xmltags;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SelectNode extends BaseNode {
	private String id;
	private String resultMap;
	private Document doc;

	public SelectNode(Document doc, String id, String resultMap) {
		super(doc);
		this.setId(id);
		this.setResultMap(resultMap);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResultMap() {
		return resultMap;
	}

	public void setResultMap(String resultMap) {
		this.resultMap = resultMap;
	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public Element build() {
		Element selectElement = doc.createElement("select");
		setAttribute(selectElement, "resultMap", getResultMap());
		setAttribute(selectElement, "id", getId());
		return selectElement;
	}

}
