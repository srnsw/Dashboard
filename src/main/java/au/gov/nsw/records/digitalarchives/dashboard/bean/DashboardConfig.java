package au.gov.nsw.records.digitalarchives.dashboard.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

public class DashboardConfig {

	ClassPathResource cpr = new ClassPathResource("dashboard.properties");
	Properties prop = new Properties();

	private static Logger logger = Logger.getLogger(DashboardConfig.class); 
	
	private String inboxPath; 
	private String droidPath;
	
	public DashboardConfig(){
		try {
			File f = cpr.getFile();
			if(f.exists()){
				// load the configuration file
				prop.load(new FileInputStream(f));

				//restore configuration
				inboxPath = prop.getProperty("inbox_path", "/mnt/inbox");
				droidPath = prop.getProperty("droid_path", "/mnt/droid/");
				logger.info("inboxPath:" + inboxPath);
			}
		} catch (IOException e) {
			logger.error(e);
		}
	}
	
	public String getInboxPath() {
		return inboxPath;
	}
	
	public String getDroidPath(){
		return droidPath;
	}
}
