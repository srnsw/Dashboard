package au.gov.nsw.records.digitalarchives.dashboard.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import au.gov.nsw.records.digitalarchives.dashboard.bean.JTableResponse;
import au.gov.nsw.records.digitalarchives.dashboard.bean.JTableResponse.Status;
import au.gov.nsw.records.digitalarchives.dashboard.bean.JTableSimpleRow;
import au.gov.nsw.records.digitalarchives.dashboard.model.ProjectRequest;
import au.gov.nsw.records.digitalarchives.dashboard.service.JsonService;

@RequestMapping("/projectrequests")
@Controller
@RooWebScaffold(path = "projectrequests", formBackingObject = ProjectRequest.class)
public class ProjectRequestController {
	
	@RequestMapping(method = RequestMethod.POST, value = "/currentuser", produces = "application/json")
  public @ResponseBody String currentUser( Model uiModel) {
//		 System.out.println("Generating... " + JsonService.toJson(new JTableResponse(Status.OK, FileRecord.findAllFileRecords())));
		List<JTableSimpleRow> rows = new ArrayList<JTableSimpleRow>();
			
			rows.add(new JTableSimpleRow("Agency Name", "<a href=\"#\">State Rockets NSW</a>"));
			rows.add(new JTableSimpleRow("Request Submitted By", "<a href=\"#\">Fred Freddy</a>"));
			rows.add(new JTableSimpleRow("Contact Details", "<b>phone:</b> 244588892 <br/> <b>address:</b> Kingswood 2748"));
			
			return JsonService.toJson(new JTableResponse(Status.OK, rows));
	}
}
