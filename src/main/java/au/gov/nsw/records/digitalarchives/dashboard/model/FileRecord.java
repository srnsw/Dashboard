package au.gov.nsw.records.digitalarchives.dashboard.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import com.google.gson.annotations.Expose;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class FileRecord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@Expose
	private Long id;

	@Expose
	private String format;
	@Expose
	private String system;
	@Expose
	private int quantity;
	@Expose
	private int size;
	@Expose
	private String description;
	@Expose
	private String issues;
}
