package com.tamguo.util;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class XMLConfiguration {
	private Document document = null;

	public boolean readConfigFile(String configFilename) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			document = db.parse(configFilename);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (document == null) {
			return false;
		}
		return true;
	}

	public boolean readConfigFile(InputStream stream) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			document = db.parse(stream);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (document == null) {
			return false;
		}
		return true;
	}

	public Document getDocument() {
		return document;
	}

	protected void setDocument(Document document) {
		this.document = document;
	}
}
