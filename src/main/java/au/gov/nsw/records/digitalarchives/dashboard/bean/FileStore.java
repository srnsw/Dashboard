package au.gov.nsw.records.digitalarchives.dashboard.bean;

import java.util.UUID;

public class FileStore {

	private String fileName;
	private long fileSize;
	private String uuid;
	
	public FileStore(String fileName, long fileSize) {
		super();
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.uuid = UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileSize() {
		return humanReadableByteCount(fileSize, true);
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public static String humanReadableByteCount(long bytes, boolean si) {
		int unit = si ? 1000 : 1024;
		if (bytes < unit) return bytes + " B";
		int exp = (int) (Math.log(bytes) / Math.log(unit));
		String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
		return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}

}
