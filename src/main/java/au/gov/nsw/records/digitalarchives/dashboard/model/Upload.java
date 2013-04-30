package au.gov.nsw.records.digitalarchives.dashboard.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Upload {
	
	private String fileName;
	
	private String extension;
	
	private String fileType;
	
	private String PUID;
	
	private String formatTypeID;
	
	private long size;
	
	private String status;
	
	private String uuid;
}
