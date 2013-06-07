package au.gov.nsw.records.digitalarchives.dashboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

public class PaginatorService {

	public <T> void populatePaginationResponse(List<T> object, int page, int pageSize, String attributePrefix, Model uiModel){
		
  	List<T> result = new ArrayList<T>(object.subList(Math.max((page-1)*pageSize, 0), Math.min(page*pageSize, object.size())));
  	float nrOfPages = (float) object.size() / pageSize;
    uiModel.addAttribute(attributePrefix + "_maxpage", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
    uiModel.addAttribute(attributePrefix +  "_page", page);
    uiModel.addAttribute(attributePrefix + "_size", pageSize);
    uiModel.addAttribute(attributePrefix, result);
	}
}
