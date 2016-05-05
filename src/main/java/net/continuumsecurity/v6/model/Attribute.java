package net.continuumsecurity.v6.model;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by pablo on 15/04/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Attribute {
	private String	attributeName;
	private String	attributeValue;

	@XmlElement(name = "attribute_name")
	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	@XmlElement(name = "attribute_value")
	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
}