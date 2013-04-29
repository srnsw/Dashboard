package au.gov.nsw.records.digitalarchives.dashboard.model;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Page {
	
	@Size(max = 255)
	private String title;
	
	//@Size(max = 90000)
	private String content;
	
	private String lastUpdateUser;
	
  @Temporal(TemporalType.DATE)
  @DateTimeFormat(style = "M-")
  private Date lastUpdate;
}
