package au.gov.nsw.records.digitalarchives.dashboard.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import au.gov.nsw.records.digitalarchives.dashboard.model.Page;
import au.gov.nsw.records.digitalarchives.dashboard.model.Person;
import au.gov.nsw.records.digitalarchives.dashboard.model.Project;
import au.gov.nsw.records.digitalarchives.dashboard.model.ProjectType;
import au.gov.nsw.records.digitalarchives.dashboard.model.Stakeholder;
import au.gov.nsw.records.digitalarchives.dashboard.model.Status;
import au.gov.nsw.records.digitalarchives.dashboard.model.StatusType;
import au.gov.nsw.records.digitalarchives.dashboard.model.Task;
import au.gov.nsw.records.digitalarchives.dashboard.model.TaskStatusType;
import au.gov.nsw.records.digitalarchives.dashboard.service.UserService;

@RequestMapping("/projects")
@Controller
@RooWebScaffold(path = "projects", formBackingObject = Project.class)
public class ProjectController {

	private static final long PROJECTPLAN_TEMPLATE = 1L;
	private static final long MIGRATIONPLAN_TEMPLATE = 2L;
	
	private static Logger logger = Logger.getLogger(ProjectController.class); 
	
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

	@RequestMapping(value = "/{id}/details", method =  RequestMethod.POST)
	@ResponseBody
	public String updateDetails(@PathVariable("id") Long id, Model uiModel,
			 @RequestParam String name,
			 @RequestParam String type,
			 @RequestParam String agencyName,
			 @RequestParam String srnswFileReference)  {
		
		logger.info(String.format("Updating project [%d] %s %s %s %s", id, name, type, agencyName, srnswFileReference));
		
		Project project = Project.findProject(id);
		
		project.setAgencyName(agencyName);
		project.setSrnswFileReference(srnswFileReference);
		project.setLastUpdateDate(new Date());
		
		project.setName(name);
		if (type.equalsIgnoreCase("physical")){
			project.setProjectType(ProjectType.Physical);
		} else if (type.equalsIgnoreCase("digital")){
			project.setProjectType(ProjectType.Digital);
		} else if (type.equalsIgnoreCase("hybrid")){
			project.setProjectType(ProjectType.Hybrid);
		}
		
		project.persist();
		
		return "ok";
	}
	

	@RequestMapping(method =  RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String create(Model uiModel,
			 @RequestParam String name,
			 @RequestParam String type,
			 @RequestParam String agencyName,
			 @RequestParam String srnswFileReference)  {
		
		logger.info(String.format("Creating project name[%s] type[%s] agencyName[%s] fileRef[%s]", name, type, agencyName, srnswFileReference));
		
		Project project = new Project();
		
		// migration plan population
		Page migrationPlanTemplate = Page.findPage(MIGRATIONPLAN_TEMPLATE);
		Page migrationPlan = new Page();
		migrationPlan.setContent(migrationPlanTemplate.getContent());
		migrationPlan.setTitle(migrationPlanTemplate.getTitle());
		migrationPlan.persist();
		project.setMigrationPlan(migrationPlan);

		// project plan population
		Page projectPlanTemplate = Page.findPage(PROJECTPLAN_TEMPLATE);
		Page projectPlan = new Page();
		projectPlan.setContent(projectPlanTemplate.getContent());
		projectPlan.setTitle(projectPlanTemplate.getTitle());
		projectPlan.persist();
		project.setProjectPlan(projectPlan);

		project.setAgencyName(agencyName);
		project.setSrnswFileReference(srnswFileReference);
		project.setLastUpdateDate(new Date());
		project.setCreationDate(new Date());
		
		project.setName(name);
		if (type.equalsIgnoreCase("physical")){
			project.setProjectType(ProjectType.Physical);
		} else if (type.equalsIgnoreCase("digital")){
			project.setProjectType(ProjectType.Digital);
		} else if (type.equalsIgnoreCase("hybrid")){
			project.setProjectType(ProjectType.Hybrid);
		}
		// save project before adding status
		project.persist();
		
		// default status population
		Status status = new Status();
		status.setProjectStatusType(StatusType.Startup);
		status.setLastUpdateDate(new Date());
		status.setProject(project);
    status.persist();
    
    // twoway population
    project.getStatus().add(status);
    // save project
    project.persist();
    
    logger.info(String.format("Created project [%d]", project.getId()));
    
    //TODO generate UUID for this project
    //TODO create folder structure for the new project in the backend
    return String.format("{\"id\": \"%d\", \"status\": \"ok\" }", project.getId());
	}
	
	  @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel, HttpServletRequest httpServletRequest) {
				
				/*Project project = new Project();
				
				// migration plan population
				Page migrationPlanTemplate = Page.findPage(MIGRATIONPLAN_TEMPLATE);
				Page migrationPlan = new Page();
				migrationPlan.setContent(migrationPlanTemplate.getContent());
				migrationPlan.setTitle(migrationPlanTemplate.getTitle());
				migrationPlan.persist();
				project.setMigrationPlan(migrationPlan);

				// project plan population
				Page projectPlanTemplate = Page.findPage(PROJECTPLAN_TEMPLATE);
				Page projectPlan = new Page();
				projectPlan.setContent(projectPlanTemplate.getContent());
				projectPlan.setTitle(projectPlanTemplate.getTitle());
				projectPlan.persist();
				project.setProjectPlan(projectPlan);

				// save project
				project.persist();
				
				// default status population
				Status status = new Status();
				status.setProjectStatusType(StatusType.Startup);
				status.setLastUpdateDate(new Date());
				status.setProject(project);
        status.persist();
        
        // twoway population
        project.getStatus().add(status);
        project.setName("[new project]");
        project.persist();
        
        //TODO generate UUID for this project
        //TODO create folder structure for the new project in the backend
              
        populateEditForm(uiModel, project);*/
        return "redirect:/projects/1" ;//+ project.getId().toString(); // + encodeUrlPathSegment(project.getId().toString(), httpServletRequest);
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("projects", Project.findProjectEntries(firstResult, sizeNo));
            float nrOfPages = (float) Project.countProjects() / sizeNo;
            uiModel.addAttribute("projects_maxpage", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
            uiModel.addAttribute("projects_page", page);
            uiModel.addAttribute("projects_size", sizeNo);
        } else {
            uiModel.addAttribute("projects", Project.findAllProjects());
        }
        addDateTimeFormatPatterns(uiModel);
        return "projects/list";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        Project project = Project.findProject(id);
        uiModel.addAttribute("project", project);
        uiModel.addAttribute("itemId", id);
        uiModel.addAttribute("status", new ArrayList<Status>(project.getStatus()));
        uiModel.addAttribute("last_status", project.getLatestStatus());
        uiModel.addAttribute("stakeholders", new ArrayList<Stakeholder>(project.getStakeholders()));
        uiModel.addAttribute("stakeholder", new Stakeholder());
        uiModel.addAttribute("members", new ArrayList<Person>(Person.findPeopleByApprovedNot(false).getResultList()));
       
        return "projects/show";
    }
}
