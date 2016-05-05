package net.continuumsecurity.v6.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by stephen on 08/02/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScanTemplates {
	private List<ScanTemplate>	templates;

	public List<ScanTemplate> getTemplates() {
		return templates;
	}

	public void setTemplates(List<ScanTemplate> templates) {
		this.templates = templates;
	}
}