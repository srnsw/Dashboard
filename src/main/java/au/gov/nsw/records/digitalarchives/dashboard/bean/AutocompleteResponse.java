package au.gov.nsw.records.digitalarchives.dashboard.bean;

import com.google.gson.annotations.Expose;

public class AutocompleteResponse {

	@Expose
	private String label;
	@Expose
	private String value;
	
	public AutocompleteResponse(String label, String value) {
		super();
		this.label = label;
		this.value = value;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
