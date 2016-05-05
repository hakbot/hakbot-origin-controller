package net.continuumsecurity.v5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;

import net.continuumsecurity.HostNotFoundException;
import net.continuumsecurity.ReportClient;
import net.continuumsecurity.v5.model.Issue;
import net.continuumsecurity.v5.model.jaxrs.Host;
import net.continuumsecurity.v5.model.jaxrs.NessusReply;
import net.continuumsecurity.v5.model.jaxrs.Port;
import net.continuumsecurity.v5.model.jaxrs.ReportItem;

/**
 * Created by stephen on 23/02/2014.
 */
public class ReportClientV5 extends SessionClientV5 implements ReportClient {
	public ReportClientV5(String nessusUrl, boolean acceptAllHostNames) {
		super(nessusUrl, acceptAllHostNames);
	}

	public List<Host> getHostsFromReport(String uuid) {
		WebTarget reportTarget = target.path("/report/hosts");
		Form form = prepopulateForm();
		form.param("report", uuid);
		NessusReply reply = sendRequestAndCheckError(reportTarget, form);
		return reply.getContents().getHost();
	}

	public List<Port> getPortsFromHost(String uuid, String hostname) {
		List<Host> hosts = getHostsFromReport(uuid);
		for(Host host : hosts){
			if(hostname.equalsIgnoreCase(host.getHostname())){
				WebTarget reportTarget = target.path("/report/ports");
				Form form = prepopulateForm();
				form.param("report", uuid);
				form.param("hostname", hostname);
				NessusReply reply = sendRequestAndCheckError(reportTarget, form);
				return reply.getContents().getPort();
			}
		}
		throw new HostNotFoundException("Hostname: " + hostname + " not found in report: " + uuid);
	}

	public List<ReportItem> getFindingsFromPort(String uuid, String host, int port, String protocol) {
		WebTarget reportTarget = target.path("/report/details");
		Form form = prepopulateForm();
		form.param("report", uuid);
		form.param("hostname", host);
		form.param("port", Integer.toString(port));
		form.param("protocol", protocol);
		NessusReply reply = sendRequestAndCheckError(reportTarget, form);
		return reply.getContents().getReportItem();
	}

	public Map<Integer, Issue> getAllIssuesSortedByPluginId(String uuid) {
		Map<Integer, Issue> issues = new HashMap<Integer, Issue>();
		for(Host host : getHostsFromReport(uuid)){
			for(Port port : getPortsFromHost(uuid, host.getHostname())){
				for(ReportItem item : getFindingsFromPort(uuid, host.getHostname(), port.getPortNum(), port.getProtocol())){
					Issue issue = issues.get(item.getPluginID());
					if(issue == null){
						issue = new Issue(nessusUrl, uuid);
						issue.setHostnames(new ArrayList<String>());
						issue.setPluginID(item.getPluginID());
						issue.setPluginName(item.getPluginName());
						issue.setPort(port.getPortNum());
						issue.setSeverity(item.getSeverity());
						issue.setProtocol(port.getProtocol());
						issue.setDescription(item.getData().getDescription());
						issue.setSolution(item.getData().getSolution());
						issue.setOutput(item.getData().getPlugin_output());
						issue.setSynopsis(item.getData().getSynopsis());
						issues.put(item.getPluginID(), issue);
					}
					issue.getHostnames().add(host.getHostname());
				}
			}
		}
		return issues;
	}
}