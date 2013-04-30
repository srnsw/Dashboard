package au.gov.nsw.records.digitalarchives.dashboard.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class RelationshipWithOtherRecords {
	
	private String relatedRecords;
	
	private String details;
	
	private String relationship;
	
	private String disposalAuthorisation;
	
	private String migrationStatus;
	
}
