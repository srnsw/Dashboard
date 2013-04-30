package au.gov.nsw.records.digitalarchives.dashboard.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class FileStorage {
	
	private String sourceFileQuantity;
	
	private String totalSourceFileSize;
	
	private String preservationFileQuantity;
	
	private String totalPreservationFileSize;
	
	private String accessFileQuantity;
	
	private String totalAcessFileSize;
	
	private String totalStorageSize;
}
