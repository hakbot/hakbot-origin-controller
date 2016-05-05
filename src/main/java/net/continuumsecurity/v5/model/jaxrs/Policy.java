package net.continuumsecurity.v5.model.jaxrs;

/**
 * Created by stephen on 22/02/2014.
 */
public class Policy {
	private int		policyID;
	private String	policyName;

	public int getPolicyID() {
		return policyID;
	}

	public void setPolicyID(int policyID) {
		this.policyID = policyID;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
}