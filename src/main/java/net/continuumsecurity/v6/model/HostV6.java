package net.continuumsecurity.v6.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by stephen on 08/02/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HostV6 {
	private String				hostname;
	private int					hostId;
	private HostInfo			info;
	private List<Vulnerability>	vulnerabilities;

	@XmlElement(name = "hostname")
	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	@XmlElement(name = "host_id")
	public int getHostId() {
		return hostId;
	}

	public void setHostId(int hostId) {
		this.hostId = hostId;
	}

	public HostInfo getInfo() {
		return info;
	}

	public void setInfo(HostInfo info) {
		this.info = info;
	}

	public List<Vulnerability> getVulnerabilities() {
		return vulnerabilities;
	}

	public void setVulnerabilities(List<Vulnerability> vulnerabilities) {
		this.vulnerabilities = vulnerabilities;
	}
}
