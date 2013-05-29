package au.gov.nsw.records.digitalarchives.dashboard.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import au.gov.nsw.records.digitalarchives.dashboard.bean.JTableResponse;
import au.gov.nsw.records.digitalarchives.dashboard.bean.JTableResponse.Status;
import au.gov.nsw.records.digitalarchives.dashboard.bean.JTableSimpleRow;
import au.gov.nsw.records.digitalarchives.dashboard.model.Person;
import au.gov.nsw.records.digitalarchives.dashboard.model.Project;
import au.gov.nsw.records.digitalarchives.dashboard.service.JsonService;

@RequestMapping("/projects")
@Controller
@RooWebScaffold(path = "projects", formBackingObject = Project.class)
public class ProjectController {

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Project.findProject(id));
        uiModel.addAttribute("stakeholders", Person.findAllPeople());
        return "projects/update";
    }
	
	@RequestMapping(value = "/{id}/files", method =  RequestMethod.GET)
	public String filemanagement(@PathVariable("id") Long id, Model uiModel, HttpServletRequest request, HttpServletResponse response)  {
		uiModel.addAttribute("project", Project.findProject(id));
		return "projects/filelist";
	}
	
	@RequestMapping(value = "/{id}/project_plan", method =  RequestMethod.GET)
	public String projectPlan(@PathVariable("id") Long id, Model uiModel)  {
		uiModel.addAttribute("page", Project.findProject(id).getProjectPlan());
		uiModel.addAttribute("project", Project.findProject(id));
		return "pages/update";
	}
	
	@RequestMapping(value = "/{id}/migration_plan", method =  RequestMethod.GET)
	public String migrationPlan(@PathVariable("id") Long id, Model uiModel)  {
		uiModel.addAttribute("page", Project.findProject(id).getMigrationPlan());
		uiModel.addAttribute("project", Project.findProject(id));
		return "pages/update";
	}
	
	@RequestMapping(value = "/{id}/format_assessment", method =  RequestMethod.GET)
	public String fileAnalysis(@PathVariable("id") Long id, Model uiModel)  {
		uiModel.addAttribute("project", Project.findProject(id));
		return "projects/formatassessment";
	}
	
	@RequestMapping(value = "/{id}/migration_process", method =  RequestMethod.GET)
	public String migrationProcess(@PathVariable("id") Long id, Model uiModel)  {
		uiModel.addAttribute("project", Project.findProject(id));
		return "projects/migrationprocess";
	}
	
	@RequestMapping(value = "/{id}/pathway_assessment", method =  RequestMethod.GET)
	public String pathwayAssessment(@PathVariable("id") Long id, Model uiModel)  {
		uiModel.addAttribute("project", Project.findProject(id));
		return "projects/pathwayassessment";
	}
	
	@RequestMapping(value = "/{id}/system_assessment", method =  RequestMethod.GET)
	public String systemAssessment(@PathVariable("id") Long id, Model uiModel)  {
		uiModel.addAttribute("project", Project.findProject(id));
		return "projects/systemassessment";
	}
	
	@RequestMapping(value = "/{id}/metadata_assessment", method =  RequestMethod.GET)
	public String metadataAssessment(@PathVariable("id") Long id, Model uiModel)  {
		uiModel.addAttribute("project", Project.findProject(id));
		return "projects/metadataassessment";
	}
	
	@RequestMapping(value = "/{id}/files/{file_id}", method =  RequestMethod.GET)
	public void download(@PathVariable("id") Long id, @PathVariable("file_id") Long file_id, HttpServletRequest request, HttpServletResponse response)  {
		
		File f = new File("c:\\nott\\ProjectPlan.docx");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + f.getName() + "\"");
		try {
			if (f.exists()){
				FileInputStream in = new FileInputStream(f);
				OutputStream out = response.getOutputStream();
				IOUtils.copy(in, out);	
				response.flushBuffer();
				out.close();
				in.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public @ResponseBody String listProjects(Model uiModel) {
//		 System.out.println("Generating... " + JsonService.toJson(new JTableResponse(Status.OK, FileRecord.findAllFileRecords())));
     return JsonService.toJson(new JTableResponse(Status.OK, Project.findAllProjects()));
  }
	
//	@RequestMapping(method = RequestMethod.POST, value = "/{id}/records", produces = "application/json")
//  public @ResponseBody String relatedRecords(@PathVariable("id") Long id, Model uiModel) {
////		 System.out.println("Generating... " + JsonService.toJson(new JTableResponse(Status.OK, FileRecord.findAllFileRecords())));
//     return JsonService.toJson(new JTableResponse(Status.OK, FileRecord.findAllFileRecords()));
//  }
	
	@RequestMapping(method = RequestMethod.POST, value = "/{id}/newrecords", produces = "application/json")
  public @ResponseBody String newRelatedRecords(@PathVariable("id") Long id, Model uiModel) {
//		 System.out.println("Generating... " + JsonService.toJson(new JTableResponse(Status.OK, FileRecord.findAllFileRecords())));
     return JsonService.toJson(new JTableResponse(Status.OK, null));
  }
	
	@RequestMapping(method = RequestMethod.POST, value = "/{id}/stakeholders", produces = "application/json")
  public @ResponseBody String relatedStakeholders(@PathVariable("id") Long id, Model uiModel) {
//		 System.out.println("Generating... " + JsonService.toJson(new JTableResponse(Status.OK, FileRecord.findAllFileRecords())));
     return JsonService.toJson(new JTableResponse(Status.OK, Person.findAllPeople()));
  }
	
	@RequestMapping(method = RequestMethod.POST, value = "/{id}/projectdrivers", produces = "application/json")
  public @ResponseBody String projectDrivers(@PathVariable("id") Long id, Model uiModel) {
//		 System.out.println("Generating... " + JsonService.toJson(new JTableResponse(Status.OK, FileRecord.findAllFileRecords())));
		List<JTableSimpleRow> rows = new ArrayList<JTableSimpleRow>();
		Project proj = Project.findProject(id);
		if (proj!=null){
			
			rows.add(new JTableSimpleRow("1", "Project driver 1"));
			rows.add(new JTableSimpleRow("2", "Another project driver"));
		}
		return JsonService.toJson(new JTableResponse(Status.OK, rows));
	}
		
	@RequestMapping(method = RequestMethod.POST, value = "/{id}/projectdetails", produces = "application/json")
  public @ResponseBody String projectDetails(@PathVariable("id") Long id, Model uiModel) {
//		 System.out.println("Generating... " + JsonService.toJson(new JTableResponse(Status.OK, FileRecord.findAllFileRecords())));
		List<JTableSimpleRow> rows = new ArrayList<JTableSimpleRow>();
		Project proj = Project.findProject(id);
		if (proj!=null){
			
			rows.add(new JTableSimpleRow("Migration Project Number", String.valueOf(id)));
			rows.add(new JTableSimpleRow("Migration Project", proj.getName()));
			rows.add(new JTableSimpleRow("Agency Name", proj.getAgencyName()));
			rows.add(new JTableSimpleRow("Agency Number", proj.getAgencyNumber()));
			rows.add(new JTableSimpleRow("Agency Contact", proj.getAgencyContact()));
			rows.add(new JTableSimpleRow("SRNSW Contact", proj.getSrnswContact()));
			rows.add(new JTableSimpleRow("SRNSW File Reference", proj.getSrnswFileReference()));
		}
		
     return JsonService.toJson(new JTableResponse(Status.OK, rows));
  }
}
