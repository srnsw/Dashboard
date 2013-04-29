package au.gov.nsw.records.digitalarchives.dashboard.service;

import java.util.ArrayList;
import java.util.List;

import au.gov.nsw.records.digitalarchives.dashboard.bean.FileStore;

public class MockupHolder {

	private static List<FileStore> fileList = new ArrayList<FileStore>();

	public static List<FileStore> getFileList() {
		return fileList;
	}

	public static void setFileList(List<FileStore> fileList) {
		MockupHolder.fileList = fileList;
	}
	
	public static void addFileStore(FileStore fs){
		fileList.add(fs);
	}
	
	
}
