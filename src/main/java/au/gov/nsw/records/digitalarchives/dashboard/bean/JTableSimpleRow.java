package au.gov.nsw.records.digitalarchives.dashboard.bean;

import com.google.gson.annotations.Expose;

public class JTableSimpleRow {

	@Expose
	private String label;
	@Expose
	private String value;
	public JTableSimpleRow(String label, String value) {
		super();
		this.label = String.format("<b>%s</b>",label);
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = String.format("<b>%s</b>",label);
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
