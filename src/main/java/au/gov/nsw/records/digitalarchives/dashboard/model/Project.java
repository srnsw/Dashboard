package au.gov.nsw.records.digitalarchives.dashboard.model;

import java.util.Date;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

	  @Enumerated(EnumType.STRING)
    private ProjectType projectType;
    
	  @Expose
    private String agencyName;
    
    @Expose
    private String agencyNumber;
    
    private String agencyContact;
    
    private String srnswContact;
    
    private String srnswFileReference;
    
    @OneToMany
    private List<Status> status;
    
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
    private Date lastUpdateDate;
    
    @Expose
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date closeDate;
    
    @ManyToOne
    private Page projectPlan;
    
    @ManyToOne
    private Page migrationPlan;
    
    @OneToMany
    private List<Stakeholder> stakeholders;
    
    @OneToMany
    private List<Upload> upload;
    
    // link to another tables
    @ManyToOne
    private Requirement requirement;
    
    //project assessment tab
    private String description;
    
    @OneToMany
    private List<Documentation> documentation;
    
    @ManyToOne
    private Record records;
    
    @OneToMany
    private List<RelationshipWithOtherRecords> relationship;
    
    @OneToMany
    private List<AccessRequirement> accessRequirement;
    
    @OneToMany
    private List<SystemAnalysis> systemAnalysis;
    
    @OneToMany
    private List<FileStorage> fileStorage;
    
    @OneToMany
    private List<DatabaseStorage> databaseStorage;
    
    @OneToMany
    private List<MigrationStrategyAssessment> migrationStrategyAssessment;
    
    // event
    @OneToMany
    private List<EventHistory> eventHistory;
    
}
