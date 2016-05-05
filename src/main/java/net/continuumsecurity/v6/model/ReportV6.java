package net.continuumsecurity.v6.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by stephen on 08/02/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportV6 {
	private List<HostV6>		hosts;
	private List<Vulnerability>	vulnerabilities;

	public List<Vulnerability> getVulnerabilities() {
		return vulnerabilities;
	}

	public void setVulnerabilities(List<Vulnerability> vulnerabilities) {
		this.vulnerabilities = vulnerabilities;
	}

	@XmlElement(name = "hosts")
	public List<HostV6> getHosts() {
		return hosts;
	}

	public void setHosts(List<HostV6> hosts) {
		this.hosts = hosts;
	}
}