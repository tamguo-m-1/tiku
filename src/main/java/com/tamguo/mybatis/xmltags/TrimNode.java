package com.tamguo.mybatis.xmltags;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TrimNode extends BaseNode {
	private String prefix;
	private String suffix;
	private String suffixOverrides;

	public TrimNode(Document doc, String prefix, String suffix, String suffixOverrides) {
		super(doc);
		this.setPrefix(prefix);
		this.setSuffix(suffix);
		this.setSuffixOverrides(suffixOverrides);
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getSuffixOverrides() {
		return suffixOverrides;
	}

	public void setSuffixOverrides(String suffixOverrides) {
		this.suffixOverrides = suffixOverrides;
	}

	@Override
	public Element build() {
		Element trimElement = getDoc().createElement("trim");
		setAttribute(trimElement, "prefix", getPrefix());
		setAttribute(trimElement, "suffix", getSuffix());
		setAttribute(trimElement, "suffixOverrides", getSuffixOverrides());
		return trimElement;
	}

}
