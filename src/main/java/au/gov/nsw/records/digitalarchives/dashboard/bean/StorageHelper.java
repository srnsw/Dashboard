package au.gov.nsw.records.digitalarchives.dashboard.bean;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;

import au.gov.nsw.records.digitalarchives.dashboard.model.Upload;

public class StorageHelper {

	@Autowired
	private DashboardConfig config;
	
	private static final String UPLOAD = "upload";
	
	public String getInboxPath(){
		return config.getInboxPath();
	}
	
	public  String getPathForProject(String projectId){
		return String.format("%s%s%s", getInboxPath(), File.separatorChar, projectId);
	}
	
	public  String getUploadPathForProject(String projectId){
		return String.format("%s%s%s", getPathForProject(projectId), File.separatorChar, UPLOAD );
	}
	
	public  String getUploadPathForUpload(String projectId, Upload upload){
		return String.format("%s%s%s%s", getUploadPathForProject(projectId), File.separatorChar, upload.getUuid(), File.separatorChar);
	}
	
	public  String getInboxPathForUpload(String projectId, Upload upload){
		return String.format("%s%s%s%s%s.%s", getUploadPathForProject(projectId), File.separatorChar, upload.getUuid(), File.separatorChar, upload.getFileName(), upload.getExtension());
	}
	
}
