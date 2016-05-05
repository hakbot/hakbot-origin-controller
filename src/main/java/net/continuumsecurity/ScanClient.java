package net.continuumsecurity;

/**
 * Created by stephen on 07/02/15.
 */
public interface ScanClient extends SessionClient {
	/**
	 * Get the status of the scan with the given scan name.
	 * @param name The scan name.
	 * @return The status of the scan.
	 * @throws ScanNotFoundException If the scan can not be found.
	 */
	public String getScanStatus(String name) throws ScanNotFoundException;

	/**
	 * Get the ID of the policy from the given name/
	 * @param name The policy name.
	 * @return The ID of the policy name.
	 * @throws PolicyNotFoundException If the policy with the given name can not be found.
	 */
	public int getPolicyIDFromName(String name) throws PolicyNotFoundException;

	/**
	 * Create a new scan against the given targets with the given policy.
	 * @param scanName The scan name.
	 * @param policyName The policy, which the scan should use.
	 * @param targets The targets.
	 * @return The ID of the created scan in NessusV6 or the UUID in NessusV5
	 */
	public String newScan(String scanName, String policyName, String targets);

	/**
	 * Launch an existing scan with the given ID.
	 * @param id The ID of the scan which exists in Nessus server.
	 */
	public void launchScan(int id);

	/**
	 * Check if the scan with the given ID is running.
	 * @param scanId the scan ID
	 * @return true if the scan is running, false otherwise.
	 */
	public boolean isScanRunning(String scanId);

	/**
	 * Get the ID from an existing scan.
	 * @param name The scan ID.
	 * @return The ID of the scan
	 */
	public int getScanIDFromName(String name);
}