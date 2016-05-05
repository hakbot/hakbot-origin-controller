package net.continuumsecurity.v5.model.jaxrs;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * Created by stephen on 23/02/2014.
 */
public class Port {
	private int			portNum;
	private String		protocol;
	private String		svcname;
	private List<Item>	item;

	@XmlElementWrapper(name = "severityCount")
	@XmlElement(name = "item")
	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}

	public int getPortNum() {
		return portNum;
	}

	public void setPortNum(int portNum) {
		this.portNum = portNum;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getSvcname() {
		return svcname;
	}

	public void setSvcname(String svcname) {
		this.svcname = svcname;
	}
}