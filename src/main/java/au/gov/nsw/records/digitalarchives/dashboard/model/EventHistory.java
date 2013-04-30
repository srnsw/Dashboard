package au.gov.nsw.records.digitalarchives.dashboard.model;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class EventHistory {
	
	private String user;
	
	private String event;
	
  @Temporal(TemporalType.TIMESTAMP)
  @DateTimeFormat(style = "M-")
  private Date timestamp;
}
