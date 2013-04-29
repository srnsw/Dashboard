package au.gov.nsw.records.digitalarchives.dashboard.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class JsonService {

	private static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create(); 
	public static String toJson(Object obj){
		System.out.println("JsonService produced:" + gson.toJson(obj));
		
		return gson.toJson(obj); 
	}
	
}
