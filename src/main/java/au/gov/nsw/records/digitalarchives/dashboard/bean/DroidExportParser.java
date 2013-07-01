package au.gov.nsw.records.digitalarchives.dashboard.bean;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVReader;

public class DroidExportParser {
	
	private static Logger logger = Logger.getLogger(DroidExportParser.class); 
	
	public List<DroidResult> readFile(String file){
		List<DroidResult> result = new ArrayList<DroidResult>();
		
		try {
			
			CSVReader reader = new CSVReader(new FileReader(file));
			
			// skip column definition
			reader.readNext();
			String [] nextLine;
			while ((nextLine = reader.readNext()) != null) {
			    // nextLine[] is an array of values from the line
				    DroidResult droid = new DroidResult();
				    droid.setParentId(nextLine[1]);
		    	  droid.setFilePath(nextLine[3]);
		    	  droid.setName(nextLine[4]);
		    	  droid.setMethod(nextLine[5]);
		    	  droid.setPuid(nextLine[14]);
		    	  droid.setFormatName(nextLine[16]);
		    	  droid.setFormatVersion(nextLine[17]);
		    	  
		    	  result.add(droid);
		    	  logger.info(droid.toString());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
