package net.continuumsecurity.v5.model.jaxrs;

/**
 * Created by stephen on 22/02/2014.
 */
public class Scan {
	private String	uuid;
	private String	readableName;
	private String	status;

	public String getReadableName() {
		return readableName;
	}

	public void setReadableName(String readableName) {
		this.readableName = readableName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}