package au.gov.nsw.records.digitalarchives.dashboard.model;

public enum DateQualifier {
	NONE(""), MONTH_AND_YEAR_ONLY("Month and year only"), BY("by"), DECADE_OF("Decade of"), QUESTIONMARK("?"), C("c."), ND("n.d."), YEAR_ONLY("year only"), LATER_THAN("later than");
	
	private String name;
	
	private DateQualifier(String name){
		this.name = name;
	}
	
	@Override 
	public String toString(){
		return name;
	}
	
	public String getName(){
		return name;
	}
}
