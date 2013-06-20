package au.gov.nsw.records.digitalarchives.dashboard.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import au.gov.nsw.records.digitalarchives.dashboard.bean.DashboardConfig;
import au.gov.nsw.records.digitalarchives.dashboard.model.EventHistory;
import au.gov.nsw.records.digitalarchives.dashboard.model.Page;
import au.gov.nsw.records.digitalarchives.dashboard.model.Person;
import au.gov.nsw.records.digitalarchives.dashboard.model.Project;
import au.gov.nsw.records.digitalarchives.dashboard.model.ProjectType;
import au.gov.nsw.records.digitalarchives.dashboard.model.Status;
import au.gov.nsw.records.digitalarchives.dashboard.model.StatusType;
import au.gov.nsw.records.digitalarchives.dashboard.model.Task;
import au.gov.nsw.records.digitalarchives.dashboard.model.TaskStatusType;
import au.gov.nsw.records.digitalarchives.dashboard.model.Upload;
import au.gov.nsw.records.digitalarchives.dashboard.service.UserService;

@RequestMapping("/projects")
@Controller
@RooWebScaffold(path = "projects", formBackingObject = Project.class)
public class ProjectController {

	private static final long PROJECTPLAN_TEMPLATE = 1L;
	private static final long MIGRATIONPLAN_TEMPLATE = 2L;
	
	private static Logger logger = Logger.getLogger(ProjectController.class); 

	@Autowired
	private DashboardConfig config;
	
	@RequestMapping(value = "/{id}/access", method =  RequestMethod.GET)
	public String access(@PathVariable("id") Long id, Model uiModel)  {
		uiModel.addAttribute("project", Project.findProject(id));
		return "projects/access";
	}
	
	@RequestMapping(value = "/{id}/members", method =  RequestMethod.POST)
	@ResponseBody
	public String addMembers(@PathVariable("id") Long id, int member, Model uiModel)  {
		
		Project project = Project.findProject(id);
		Person person = Person.findPerson(Long.valueOf(member)); 
		project.getStakeholders().add(person);
		project.persist();
		
		return "{\"status\": \"ok\"}";
	}
	
	@RequestMapping(value = "/{id}/files", method =  RequestMethod.POST)
	@ResponseBody
	public String addFile(@PathVariable("id") Long id, Model uiModel, MultipartFile content, HttpServletRequest request)  {
		
	  if (!(request instanceof MultipartHttpServletRequest)) {
      //error(resp, "Invalid request (multipart request expected)");
      //lreturn null;
  		System.out.println("not multipart request!!");
  	}
	  
	  Project project = Project.findProject(id);
		Upload upload = new Upload();
		
  	Map<String, MultipartFile> files = ((MultipartHttpServletRequest)request).getFileMap();
  	try{
	  	for (String f:files.keySet()){
	  		MultipartFile mp = files.get(f);
	  		System.out.println("file_name:" + mp.getOriginalFilename());
	  		System.out.println("file_size:" + mp.getSize());
	  		
	  		String destinationFile = config.getInboxPath() + project.getId() + "/upload/" + upload.getUuid() + mp.getOriginalFilename();
	  		
	  		File destination = new File(destinationFile);
	  		mp.transferTo(destination);

	  	}
	  	
  	}catch(IOException e){
  		e.printStackTrace();
  	}
  	
//		String inbox = config.getInboxPath();
		
		
		
		upload.persist();
		
		project.getUpload().add(upload);
		project.persist();

		return "{\"status\": \"ok\"}";
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
		// auto populate creator to be a stakeholder
		project.getStakeholders().add(UserService.getLoggedinUser());
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
    
    // add event history
 		EventHistory event = new EventHistory();
 		event.setEvent(String.format("Created project #" + project.getId()));
 		event.setProject(project);
 		event.setUser(UserService.getLoggedinUser());
 		event.persist();
 		
    logger.info(String.format("Created project [%d]", project.getId()));
    
    try {
	    String inboxPath = String.format("%s%s%s",config.getInboxPath(), File.separatorChar, Long.toString(project.getId()));
	    logger.info(String.format("creating %s", inboxPath));
			FileUtils.forceMkdir(new File(inboxPath));

			String uploadPath = String.format("creating %s%s%s", inboxPath, File.separatorChar, "upload");
			logger.info(String.format("creating %s", uploadPath));
			FileUtils.forceMkdir(new File(uploadPath));
			
			String etcPath = String.format("creating %s%s%s", inboxPath, File.separatorChar, "etc");
			logger.info(String.format("creating %s", etcPath));
			FileUtils.forceMkdir(new File(etcPath));
			
			logger.info("Created initial directories for project [" + project.getId() + "]" );
		} catch (Exception e) {
			logger.error(e);
		}
    
    return String.format("{\"id\": \"%d\", \"status\": \"ok\" }", project.getId());
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
		
		// create task
		Task task = new Task();
		task.setProject(project);
		task.setCreated(new Date());
		task.setCreatedBy(UserService.getLoggedinUser());
		task.setDescription(description);
		task.setStatus(TaskStatusType.Open);
		task.setDue(due);
		task.setAssignedTo(person);
		task.persist();
		
		project.getTask().add(task);
		project.persist();
		
		// add event history
		EventHistory event = new EventHistory();
		event.setEvent(String.format("Created task #%d", task.getId()));
		event.setProject(project);
		event.setUser(UserService.getLoggedinUser());
		event.persist();
		
		//uiModel.addAttribute("page", Project.findProject(id).getProjectPlan());
		//uiModel.addAttribute("project", Project.findProject(id));
		return "ok";
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
	
	@RequestMapping(value = "/{id}/format_assessment", method =  RequestMethod.GET)
	public String fileAnalysis(@PathVariable("id") Long id, Model uiModel)  {
		uiModel.addAttribute("project", Project.findProject(id));
		return "projects/formatassessment";
	}
	
	@RequestMapping(value = "/{id}/files", method =  RequestMethod.GET)
	public String filemanagement(@PathVariable("id") Long id, Model uiModel, HttpServletRequest request, HttpServletResponse response)  {
		uiModel.addAttribute("project", Project.findProject(id));
		return "projects/filelist";
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
	
	@RequestMapping(value = "/{id}/metadata_assessment", method =  RequestMethod.GET)
	public String metadataAssessment(@PathVariable("id") Long id, Model uiModel)  {
		uiModel.addAttribute("project", Project.findProject(id));
		return "projects/metadataassessment";
	}
	
	@RequestMapping(value = "/{id}/migration_plan", method =  RequestMethod.GET)
	public String migrationPlan(@PathVariable("id") Long id, Model uiModel)  {
		uiModel.addAttribute("page", Project.findProject(id).getMigrationPlan());
		uiModel.addAttribute("project", Project.findProject(id));
		return "pages/update";
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
	
	@RequestMapping(value = "/{id}/project_plan", method =  RequestMethod.GET)
	public String projectPlan(@PathVariable("id") Long id, Model uiModel)  {
		uiModel.addAttribute("page", Project.findProject(id).getProjectPlan());
		uiModel.addAttribute("project", Project.findProject(id));
		return "pages/update";
	}

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        Project project = Project.findProject(id);
        uiModel.addAttribute("project", project);
        uiModel.addAttribute("itemId", id);
        uiModel.addAttribute("status", new ArrayList<Status>(Status.findStatusesByProject(project).getResultList()));
        uiModel.addAttribute("last_status", project.getLatestStatus());
        uiModel.addAttribute("stakeholders", new ArrayList<Person>(project.getStakeholders()));
        uiModel.addAttribute("members", new ArrayList<Person>(Person.findPeopleByApprovedNot(false).getResultList()));
        uiModel.addAttribute("all_projects", new ArrayList<Project>(Project.findAllProjects()));
        uiModel.addAttribute("tasks", new ArrayList<Task>(project.getTask()));
        uiModel.addAttribute("events", new ArrayList<EventHistory>(EventHistory.findEventHistorysByProject(project).getResultList()));
        
        return "projects/show";
    }
	
	@RequestMapping(value = "/{id}/system_assessment", method =  RequestMethod.GET)
	public String systemAssessment(@PathVariable("id") Long id, Model uiModel)  {
		uiModel.addAttribute("project", Project.findProject(id));
		return "projects/systemassessment";
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
		
		// log event
		EventHistory event = new EventHistory();
		event.setProject(project);
		event.setUser(UserService.getLoggedinUser());
		event.setEvent("Updated project details #" + project.getId());
		event.persist();
		
		project.persist();
		
		return "{\"status\": \"ok\"}";
	}
	
	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Project.findProject(id));
        uiModel.addAttribute("stakeholders", Person.findAllPeople());
        return "projects/update";
    }

	@RequestMapping(value = "/{id}/status", method =  RequestMethod.POST)
	@ResponseBody
	public String updateStatus(@PathVariable("id") Long id, Model uiModel,
			 @RequestParam String comment,
			 @RequestParam String status)  {
		try{
			logger.info(String.format("Updating project status [%d] %s %s", id, status, comment));
			
			Project project = Project.findProject(id);
			
			// default status population
			Status prjStatus = new Status();
			prjStatus.setProjectStatusType(StatusType.fromInt(Integer.valueOf(status)));
			prjStatus.setLastUpdateDate(new Date());
			prjStatus.setProject(project);
			prjStatus.setComment(comment);
			prjStatus.persist();
	    
	    // twoway population
	    project.getStatus().add(prjStatus);
			
			// log event
			EventHistory event = new EventHistory();
			event.setProject(project);
			event.setUser(UserService.getLoggedinUser());
			event.setEvent("Updated project status #" + project.getId() + " to " + prjStatus.getProjectStatusType().toString());
			event.persist();
			
			project.setLastUpdateDate(new Date());
			project.persist();
		
			return "{\"status\": \"ok\"}";
		}catch (Exception e){
			e.printStackTrace();
			return "{\"status\": \"error\"}";
		}
	}
}
