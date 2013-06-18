package au.gov.nsw.records.digitalarchives.dashboard.model;

import java.util.HashMap;
import java.util.Map;

public enum StatusType {

	Startup(1), Assessment(2), MigrationPlan(3), Migration(4), OnHold(5), Discontinued(6), Terminated(7), Closed(8);
	
	private final int value;  
	
	StatusType(int value) {
    this.value= value;
  }
	
	private static final Map<Integer, StatusType> intToTypeMap = new HashMap<Integer, StatusType>();
	static {
	    for (StatusType type : StatusType.values()) {
	        intToTypeMap.put(type.value, type);
	    }
	}

	public static StatusType fromInt(int i) {
		StatusType type = intToTypeMap.get(Integer.valueOf(i));
	    if (type == null) 
	        return StatusType.Startup;
	    return type;
	}
}
