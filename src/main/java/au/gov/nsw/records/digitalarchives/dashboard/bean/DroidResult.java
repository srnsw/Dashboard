package au.gov.nsw.records.digitalarchives.dashboard.bean;

public class DroidResult {
	
	private String id;
	private String parentId;
	private String uri;
	private String filePath;
	private String name;
	private String method;
	private String status;
	private String size;
	private String type;
	private String extension;
	private String lastModified;
	private String extensionMismatch;
	private String md5;
	private String formatCount;
	private String puid;
	private String mimeType;
	private String formatName;
	private String formatVersion;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getLastModified() {
		return lastModified;
	}
	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}
	public String getExtensionMismatch() {
		return extensionMismatch;
	}
	public void setExtensionMismatch(String extensionMismatch) {
		this.extensionMismatch = extensionMismatch;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public String getFormatCount() {
		return formatCount;
	}
	public void setFormatCount(String formatCount) {
		this.formatCount = formatCount;
	}
	public String getPuid() {
		return puid;
	}
	public void setPuid(String puid) {
		this.puid = puid;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getFormatName() {
		return formatName;
	}
	public void setFormatName(String formatName) {
		this.formatName = formatName;
	}
	public String getFormatVersion() {
		return formatVersion;
	}
	public void setFormatVersion(String formatVersion) {
		this.formatVersion = formatVersion;
	}
	@Override
	public String toString() {
		return "DroidResult [id=" + id + ", parentId=" + parentId + ", uri=" + uri + ", filePath=" + filePath + ", name=" + name + ", method=" + method + ", status=" + status + ", size=" + size + ", type=" + type + ", extension=" + extension
				+ ", lastModified=" + lastModified + ", extensionMismatch=" + extensionMismatch + ", md5=" + md5 + ", formatCount=" + formatCount + ", puid=" + puid + ", mimeType=" + mimeType + ", formatName=" + formatName + ", formatVersion="
				+ formatVersion + "]";
	}
	
	
}
