package net.continuumsecurity.v5;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;

import net.continuumsecurity.NessusException;
import net.continuumsecurity.PolicyNotFoundException;
import net.continuumsecurity.ScanClient;
import net.continuumsecurity.ScanNotFoundException;
import net.continuumsecurity.v5.model.jaxrs.NessusReply;
import net.continuumsecurity.v5.model.jaxrs.Policy;
import net.continuumsecurity.v5.model.jaxrs.Scan;

/**
 * Created by stephen on 21/02/2014.
 */
public class ScanClientV5 extends SessionClientV5 implements ScanClient {
	public ScanClientV5(String nessusUrl, boolean acceptAllHostNames) {
		super(nessusUrl, acceptAllHostNames);
	}

	public String getScanStatus(String name) throws ScanNotFoundException {
		WebTarget scanTarget = target.path("/scan/list");
		Form form = prepopulateForm();
		NessusReply reply = sendRequestAndCheckError(scanTarget, form);
		for(Scan scan : reply.getContents().getScans().getScan()){
			if(name.equalsIgnoreCase(scan.getReadableName())){
				return scan.getStatus();
			}
		}
		throw new ScanNotFoundException("No scan with name: " + name);
	}

	public int getPolicyIDFromName(String name) throws PolicyNotFoundException {
		WebTarget scanTarget = target.path("/policy/list");
		Form form = prepopulateForm();
		NessusReply reply = sendRequestAndCheckError(scanTarget, form);
		for(Policy policy : reply.getContents().getPolicies()){
			if(name.equalsIgnoreCase(policy.getPolicyName()))
				return policy.getPolicyID();
		}
		throw new PolicyNotFoundException("No policy with name: " + name);
	}

	public String newScan(String scanName, String policyName, String targets) {
		//first get the policy ID for the name
		int policyId = getPolicyIDFromName(policyName);
		WebTarget scanTarget = target.path("/scan/new");
		Form form = prepopulateForm();
		form.param("scan_name", scanName);
		form.param("target", targets);
		form.param("policy_id", Integer.toString(policyId));
		NessusReply reply = sendRequestAndCheckError(scanTarget, form);
		return reply.getContents().getScan().getUuid();
	}

	public boolean isScanRunning(String scanName) {
		try{
			getScanStatus(scanName);
			return true;
		}catch(ScanNotFoundException e){
			return false;
		}
	}

	@Override
	public int getScanIDFromName(String name) {
		throw new NessusException("Not supported");
	}

	@Override
	public void launchScan(int id) {
		throw new NessusException("Not supported");
	}

}