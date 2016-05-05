package net.continuumsecurity.v6.model;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by pablo on 15/04/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HostInfo {
	private String	hostStart;
	private String	macAddress;
	private String	hostFQDN;
	private String	hostEnd;
	private String	operatingSystem;
	private String	hostIP;

	@XmlElement(name = "host_start")
	public String getHostStart() {
		return hostStart;
	}

	public void setHostStart(String hostStart) {
		this.hostStart = hostStart;
	}

	@XmlElement(name = "mac-address")
	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	@XmlElement(name = "host-fqdn")
	public String getHostFQDN() {
		return hostFQDN;
	}

	public void setHostFQDN(String hostFQDN) {
		this.hostFQDN = hostFQDN;
	}

	@XmlElement(name = "host_end")
	public String getHostEnd() {
		return hostEnd;
	}

	public void setHostEnd(String hostEnd) {
		this.hostEnd = hostEnd;
	}

	@XmlElement(name = "operating-system")
	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	@XmlElement(name = "host-ip")
	public String getHostIP() {
		return hostIP;
	}

	public void setHostIP(String hostIP) {
		this.hostIP = hostIP;
	}
}
