package au.gov.nsw.records.digitalarchives.dashboard.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import au.gov.nsw.records.digitalarchives.dashboard.model.Person;
import au.gov.nsw.records.digitalarchives.dashboard.model.Project;
import au.gov.nsw.records.digitalarchives.dashboard.model.Status;
import au.gov.nsw.records.digitalarchives.dashboard.model.StatusType;
import au.gov.nsw.records.digitalarchives.dashboard.model.Task;
import au.gov.nsw.records.digitalarchives.dashboard.model.TaskStatusType;
import au.gov.nsw.records.digitalarchives.dashboard.service.UserService;

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
	
	//TODO user role validation
	@RequestMapping(value = "/{id}/tasks", method =  RequestMethod.POST)
	@ResponseBody
	public String createTask(@PathVariable("id") Long id, Model uiModel,
			 @RequestParam String description,
			 @RequestParam Date due,
			 @RequestParam int assignedTo)  {
		
		Project project = Project.findProject(id);
		
		Person person = Person.findPerson(Long.valueOf(assignedTo)); 
		
		Task task = new Task();
		task.setProject(project);
		task.setCreated(new Date());
		task.setCreatedBy(UserService.getLoggedinUser());
		task.setDescription(description);
		task.setStatus(TaskStatusType.Open);
		task.setDue(due);
		task.setAssignedTo(person);
		task.persist();
		project.persist();
		//uiModel.addAttribute("page", Project.findProject(id).getProjectPlan());
		//uiModel.addAttribute("project", Project.findProject(id));
		return "ok";
	}
	
	//TODO
	@RequestMapping(value = "/{id}/tasks", method =  RequestMethod.GET)
	public String getTask(@PathVariable("id") Long id, Model uiModel)  {
//		Project project = Project.findProject(id);
//		
//		Task task = new Task();
//		task.setProject(project);
//		
//		task.persist();
//		
//		uiModel.addAttribute("page", Project.findProject(id).getProjectPlan());
//		uiModel.addAttribute("project", Project.findProject(id));
		return "pages/update";
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


	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel, HttpServletRequest httpServletRequest) {
				
				Project project = new Project();
				Status status = new Status();
				status.setProjectStatusType(StatusType.Startup);
				status.setLastUpdateDate(new Date());
				status.setProject(project);
        
				project.persist();
        status.persist();
        
        populateEditForm(uiModel, project);
        return "redirect:/projects/" + project.getId().toString(); // + encodeUrlPathSegment(project.getId().toString(), httpServletRequest);
    }
}
