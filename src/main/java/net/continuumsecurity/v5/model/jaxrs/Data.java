package net.continuumsecurity.v5.model.jaxrs;

/**
 * Created by stephen on 23/02/2014.
 */
public class Data {
	private String	solution;
	private String	risk_factor;
	private String	description;
	private String	synopsis;
	private String	plugin_output;

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getRisk_factor() {
		return risk_factor;
	}

	public void setRisk_factor(String risk_factor) {
		this.risk_factor = risk_factor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getPlugin_output() {
		return plugin_output;
	}

	public void setPlugin_output(String plugin_output) {
		this.plugin_output = plugin_output;
	}
}