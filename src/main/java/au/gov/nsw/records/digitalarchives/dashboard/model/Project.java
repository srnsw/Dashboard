package au.gov.nsw.records.digitalarchives.dashboard.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import com.google.gson.annotations.Expose;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Project {

	  @Id
	  @Expose
		@GeneratedValue(strategy = GenerationType.AUTO)
	  private Long id;
	
	  @Expose
    private String name;

	  @Expose
    private String agencyName;
    
    @Expose
    private String agencyNumber;
    
    private String agencyContact;
    
    private String srnswContact;
    
    private String srnswFileReference;
    
    private String migrationPlanDescription;
    
    @Expose
    private String status;
    
    @Expose
    private String lastUpdatedBy;
    
    @Expose
    private String overview;
    
    @Expose
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date creationDate;
    
    @Expose
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date lastUpdate;
}
