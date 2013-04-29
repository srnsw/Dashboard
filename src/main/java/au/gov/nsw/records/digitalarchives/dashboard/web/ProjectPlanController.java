package au.gov.nsw.records.digitalarchives.dashboard.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import au.gov.nsw.records.digitalarchives.dashboard.model.ProjectPlan;

@RequestMapping("/projectplans")
@Controller
@RooWebScaffold(path = "projectplans", formBackingObject = ProjectPlan.class)
public class ProjectPlanController {
	
	@RequestMapping
  public String index(HttpServletRequest request) {
  return "projectplans/index";
  }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("projectplan", ProjectPlan.findProjectPlan(id));
        uiModel.addAttribute("itemId", id);
        
        List<String> objectives = new ArrayList<String>();
        objectives.add("A fully accountable and documented transfer process");
        objectives.add("Successful migration of digital records to the State Records Digital Archive");
        objectives.add("Management of source records in line with GA 33 (disposal authorisation for source records that have been migrated)");
        objectives.add("The capture of adequate information by State Records about the records and their associated requirements to allow them to be preserved, protected and made available in accordance with provisions of the State Records Act 1998");
        uiModel.addAttribute("objectives", objectives);
        

        List<String> deliverables = new ArrayList<String>();
        deliverables.add("Registration of record series information");
        deliverables.add("Description of the systems planned for migration");
        deliverables.add("Approval and registration of access directions covering the records that will be transferred to State Records NSW");
        deliverables.add("Approved Migration Plan");
        deliverables.add("Process for transfer of physical format records"); 
        deliverables.add("Approved Distributed Management Agreement");
        deliverables.add("Determination on source records in line with GA 33");
        deliverables.add("The transfer of custody and control of the records from [INSERT NAME] to the State Records Authority"); 
        deliverables.add("The transfer of control to State Records NSW with custody retained by [INSERT NAME]");
        uiModel.addAttribute("deliverables", deliverables);

        List<String> transferrequirements = new ArrayList<String>();
        transferrequirements.add("Series registration");
        transferrequirements.add("System description");
        transferrequirements.add("Access direction registration");
        transferrequirements.add("Agency registration");
        transferrequirements.add("Migration plan");
        transferrequirements.add("Consignment/item listing");
        transferrequirements.add("Conservation assessment and treatment");
        transferrequirements.add("Distributed management agreement");
        uiModel.addAttribute("transferrequirements", transferrequirements);
        
        List<String> prjInscope = new ArrayList<String>();
        prjInscope.add("Assessment of the records and requirements for their management by State Records");
        prjInscope.add("Preparation of a Migration Plan by State Records");
        prjInscope.add("Approval of the Migration Plan by [INSERT NAME]");
        prjInscope.add("Migration of records into the State Records Digital Archive");
        prjInscope.add("Preparation of a Distributed Management Agreement");
        prjInscope.add("Approval of the Distributed Management Agreement");
        prjInscope.add("Completion of Transfer documentation");
        prjInscope.add("Transfer of physical format records to State Records");
        prjInscope.add("Resolving errors and issues during the migration");
        prjInscope.add("State Records providing official confirmation of the transfer of custody and control of the records");
        prjInscope.add("Advice to [INSERT NAME] on the management of the source records");
        uiModel.addAttribute("prjInscope", prjInscope);
        
        List<String> prjOutscope = new ArrayList<String>();
        prjOutscope.add("ADD TEXT");
        uiModel.addAttribute("prjOutscope", prjOutscope);
        
        List<String> srnswResponsible = new ArrayList<String>();
        srnswResponsible.add("Project management");
        srnswResponsible.add("Assessment activities");
        srnswResponsible.add("Development of the Migration Plan");
        srnswResponsible.add("Processing the migration");
        srnswResponsible.add("Making any required changes to the Migration Plan");
        srnswResponsible.add("Receipt of records for transfer");
        srnswResponsible.add("Checking transferred physical format records against consignment lists");
        srnswResponsible.add("Confirmation to [INSERT NAME] on the transfer of custody and control of the records");
        srnswResponsible.add("Advice on the retention and/or destruction of the source records");
        uiModel.addAttribute("srnswResponsible", srnswResponsible);
        
        List<String> agencyResponsible = new ArrayList<String>();
        agencyResponsible.add("Approval of the Archives Project Plan from State Records");
        agencyResponsible.add("Supplying  State Records with information about the records and the business they document as required");
        agencyResponsible.add("Approval of the Migration Plan developed by State Records");
        agencyResponsible.add("Providing State Records with required record listings for transfer");
        agencyResponsible.add("Providing State Records with the records for transfer");
        agencyResponsible.add("Ensuring the requirements for the retention and/or destruction of the source records are adhered to as outlined in GA 33");
        uiModel.addAttribute("agencyResponsible", agencyResponsible);
        
        return "projectplans/show";
        
    }
}
