package au.gov.nsw.records.digitalarchives.dashboard.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class DatabaseStorage {
	
	private String sourceDatabaseSize;
	
	private String migratinSize;
	
	private String totalStorageSize;
	
}
