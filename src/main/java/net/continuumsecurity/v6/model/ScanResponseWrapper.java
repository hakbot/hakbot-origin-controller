package net.continuumsecurity.v6.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by stephen on 08/02/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScanResponseWrapper {
	private ScanResponse	scan;
	private String			error;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public ScanResponse getScan() {
		return scan;
	}

	public void setScan(ScanResponse scan) {
		this.scan = scan;
	}
}