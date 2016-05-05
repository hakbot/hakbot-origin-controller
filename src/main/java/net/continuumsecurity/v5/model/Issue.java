package net.continuumsecurity.v5.model;

import java.util.List;

import net.continuumsecurity.v6.model.HostV6;

/**
 * Created by stephen on 23/02/2014.
 */
public class Issue {
	private int				pluginID;
	private int				port;
	private int				severity;
	private String			protocol;
	private List<HostV6>	hostsV6;
	private List<String>	hostnames;
	private String			description;
	private String			solution;
	private String			output;
	private String			synopsis;
	private String			nessusUrl, scanId;

	public Issue(String nessusUrl, String scanId) {
		this.nessusUrl = nessusUrl;
		this.scanId = scanId;
	}

	public String getPluginName() {
		return pluginName;
	}

	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}

	private String	pluginName;

	public int getPluginID() {
		return pluginID;
	}

	public void setPluginID(int pluginID) {
		this.pluginID = pluginID;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getSolution() {
		return solution;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getOutput() {
		return output;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public List<String> getHostnames() {
		return hostnames;
	}

	public void setHostnames(List<String> hostnames) {
		this.hostnames = hostnames;
	}

	public List<HostV6> getHostsV6() {
		return hostsV6;
	}

	public void setHostsV6(List<HostV6> hostsV6) {
		this.hostsV6 = hostsV6;
	}

	public String buildV6Url(HostV6 hostV6) {
		StringBuilder sb = new StringBuilder();
		return sb.append(nessusUrl + "/nessus6.html#/scans/").append(scanId).append("/hosts/").append(hostV6.getHostId()).append("/vulnerabilities/").append(pluginID).toString();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder().append("ID: ").append(pluginID).append("\n").append("Name: ").append(pluginName).append("\n").append("Description: ").append(description).append("\n").append("Severity: ").append(severity).append("\n").append("Hosts:\n");
		if(hostsV6 != null){
			for(HostV6 hostV6 : getHostsV6()){
				sb.append("\t\t" + hostV6.getHostname() + "\n");
				sb.append("\t\tUrl: " + buildV6Url(hostV6) + "\n");
			}
		}else{
			for(String hostname : getHostnames()){
				sb.append("\t\t" + hostname + "\n");
			}
		}
		return sb.toString();
	}
}