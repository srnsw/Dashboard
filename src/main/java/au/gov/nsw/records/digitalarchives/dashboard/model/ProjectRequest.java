package au.gov.nsw.records.digitalarchives.dashboard.model;

import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class ProjectRequest {
	
	@ManyToOne
	private Person requestPerson;
	
	private String reason;
	
	private String overview;
	
	private boolean disposalAuthorisationRegistered;
	
	private String disposalAuthorisationNumber;
	
	private boolean accessDirectionRegistered;
	
	private String accessDirectionNumber;
	
	private boolean recordsBeingUsed;
	
	private String relatedPhysicalRecords;
	
  @Temporal(TemporalType.DATE)
  @DateTimeFormat(style = "M-")
  private Date dateStart;
  
  private DateQualifier dateStartQualifier;
  
  @Temporal(TemporalType.DATE)
  @DateTimeFormat(style = "M-")
  private Date dateEnd;
  
  private DateQualifier dateEndQualifier;
}
