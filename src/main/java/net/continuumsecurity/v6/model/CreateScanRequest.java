package net.continuumsecurity.v6.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by stephen on 07/02/15.
 */
@XmlRootElement
public class CreateScanRequest {
	private String		uuid;
	private Settings	settings;

	@XmlElement
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@XmlElement
	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}
}