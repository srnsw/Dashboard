package au.gov.nsw.records.digitalarchives.dashboard.bean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class DroidHelper {

	private static Logger logger = Logger.getLogger(DroidHelper.class);
	
	@Autowired
	DashboardConfig dashboardConfig;
	
	public List<DroidResult> analyse(String path, String projectId){
		logger.info("Analysing " + path);
		String droid = dashboardConfig.getDroidPath();
		
		String[] createProfile = {"java","-jar", droid , "-R", "-a", path, "-p", path + "\\droid.droid"};
		
		String line;
		StringBuffer sb = new StringBuffer();

		try {
			
			logger.info("executing:" + Arrays.toString(createProfile));
			
			Process p = Runtime.getRuntime().exec(createProfile);
			BufferedReader input = new BufferedReader
					(new InputStreamReader(p.getInputStream()));
			while ((line = input.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
			
			logger.info(sb.toString());
			
			input.close();
			
			// read export file
		} catch (Exception e) {
			logger.error(e);
		}

		String[] createExport = {"java","-jar", droid , "-p", path + "\\droid.droid", "-e", path + "\\droid.csv"};
		
	try {
			
			logger.info("executing:" + Arrays.toString(createProfile));
			
			Process p = Runtime.getRuntime().exec(createExport);
			BufferedReader input = new BufferedReader
					(new InputStreamReader(p.getInputStream()));
			while ((line = input.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
			
			logger.info(sb.toString());
			
			input.close();
			
		} catch (Exception e) {
			logger.error(e);
		}
	
		DroidExportParser parser = new DroidExportParser();
		return parser.readFile(path + "\\droid.csv");
	}
	
	public void isAnalysing(String path){
		logger.info("Checking " + path);
	}
	
}
