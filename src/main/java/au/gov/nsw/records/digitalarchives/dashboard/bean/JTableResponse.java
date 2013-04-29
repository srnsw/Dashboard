package au.gov.nsw.records.digitalarchives.dashboard.bean;

import java.util.List;

import com.google.gson.annotations.Expose;

public class JTableResponse {
	
	public enum Status {OK, ERROR}
	
	//starting variable name with a capital letter made JTable happy
	@Expose
	private Status Result = Status.OK;
	@Expose
	private List<?> Records;
	
	public JTableResponse(Status result, List<?> records) {
		super();
		this.Result = result;
		this.Records = records;
	}

	public Status getResult() {
		return Result;
	}

	public void setResult(Status result) {
		this.Result = result;
	}

	public List<?> getRecords() {
		return Records;
	}

	public void setRecords(List<?> records) {
		this.Records = records;
	}
	
	
}
