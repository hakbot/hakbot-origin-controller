package net.continuumsecurity.v5.model.jaxrs;

/**
 * Created by stephen on 23/02/2014.
 */
public class Host {
	private String	hostname;
	private int		severity;

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
}