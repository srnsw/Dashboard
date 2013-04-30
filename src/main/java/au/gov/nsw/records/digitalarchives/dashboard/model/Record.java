package au.gov.nsw.records.digitalarchives.dashboard.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Record {
	
	private String details;
	
	private String disposalAuthroisation;
	
	private String access;
	
	private String sizeOrQuantity;
	
	private String relationships;
	
	private String dateRange;
}
