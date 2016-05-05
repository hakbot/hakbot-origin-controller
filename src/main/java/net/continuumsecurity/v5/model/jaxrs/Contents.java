package net.continuumsecurity.v5.model.jaxrs;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * Created by stephen on 22/02/2014.
 */
public class Contents {
	private String				token;
	private Scans				scans;
	private List<Policy>		policies;
	private Scan				scan;
	private List<Host>			host;
	private List<Port>			port;
	private List<ReportItem>	reportItem;

	@XmlElementWrapper(name = "portDetails")
	@XmlElement(name = "ReportItem")
	public List<ReportItem> getReportItem() {
		return reportItem;
	}

	public void setReportItem(List<ReportItem> reportItem) {
		this.reportItem = reportItem;
	}

	@XmlElementWrapper(name = "policies")
	@XmlElement(name = "policy")
	public List<Policy> getPolicies() {
		return policies;
	}

	public void setPolicies(List<Policy> policies) {
		this.policies = policies;
	}

	public Scans getScans() {
		return scans;
	}

	public void setScans(Scans scans) {
		this.scans = scans;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Scan getScan() {
		return scan;
	}

	public void setScan(Scan scan) {
		this.scan = scan;
	}

	@XmlElementWrapper(name = "hostList")
	@XmlElement(name = "host")
	public List<Host> getHost() {
		return host;
	}

	public void setHost(List<Host> host) {
		this.host = host;
	}

	@XmlElementWrapper(name = "portList")
	@XmlElement(name = "port")
	public List<Port> getPort() {
		return port;
	}

	public void setPort(List<Port> port) {
		this.port = port;
	}
}