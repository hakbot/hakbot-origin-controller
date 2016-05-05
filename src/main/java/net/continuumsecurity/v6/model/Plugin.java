package net.continuumsecurity.v6.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by pablo on 15/04/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Plugin {
	private List<Attribute>	attributes;
	private String			familyName;
	private String			name;
	private int				id;

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

	@XmlElement(name = "family_name")
	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}