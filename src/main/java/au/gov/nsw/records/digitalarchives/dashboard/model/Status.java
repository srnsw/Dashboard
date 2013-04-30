package au.gov.nsw.records.digitalarchives.dashboard.model;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Status {
	

  @Enumerated(EnumType.STRING)
  private StatusType projectStatusType;
  
  @Temporal(TemporalType.TIMESTAMP)
  @DateTimeFormat(style = "M-")
  private Date lastUpdateDate;
  
  private String comment;
}
