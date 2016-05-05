package net.continuumsecurity.v5.model.jaxrs;

/**
 * Created by stephen on 23/02/2014.
 */
public class Item {
	private int	severityLevel;
	private int	count;

	public int getSeverityLevel() {
		return severityLevel;
	}

	public void setSeverityLevel(int severityLevel) {
		this.severityLevel = severityLevel;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}