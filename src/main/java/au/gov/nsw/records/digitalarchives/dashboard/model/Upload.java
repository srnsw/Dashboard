package au.gov.nsw.records.digitalarchives.dashboard.model;

import java.util.UUID;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findUploadsByPUIDEquals" })
public class Upload {

    private String fileName;

    private String extension;

    private String fileType;

    private String PUID;

    private String formatTypeID;

    private long size;

    private String status;

    private String uuid = UUID.randomUUID().toString().replaceAll("-", "");
    
    public String getFormattedSize(){
    	return formatStringByteCount(size, true);
    }
    private String formatStringByteCount(long bytes, boolean si) {
  		int unit = si ? 1000 : 1024;
  		if (bytes < unit) return bytes + " B";
  		int exp = (int) (Math.log(bytes) / Math.log(unit));
  		String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
  		return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
  	}
}
