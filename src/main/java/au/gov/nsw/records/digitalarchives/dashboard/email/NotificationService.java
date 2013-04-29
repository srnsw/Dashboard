package au.gov.nsw.records.digitalarchives.dashboard.email;


public interface NotificationService {
	public void sendMessage(String mailTo, String message);
}
