package au.gov.nsw.records.digitalarchives.dashboard.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import au.gov.nsw.records.digitalarchives.dashboard.model.Person;

@RequestMapping("/admin/**")
@Controller
public class AdminController {

		private static int sizeNo = 5;
		
    @RequestMapping(method = RequestMethod.POST, value = "{id}")
    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    @RequestMapping
    public String index(@RequestParam(value = "user_appr_page", required = false, defaultValue="1") Integer user_appr_page, @RequestParam(value = "user_appr_size", required = false, defaultValue="30") Integer user_appr_size, Model uiModel) {
    	
    	List<Person> persons = Person.findPeopleByApprovedNot(true).getResultList();
    	List<Person> person_result = new ArrayList<Person>(persons.subList(Math.max((user_appr_page-1)*sizeNo, 0), Math.min(user_appr_page*sizeNo, persons.size())));
    	float nrOfPages = (float) persons.size() / sizeNo;
      uiModel.addAttribute("user_appr_maxpage", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
      uiModel.addAttribute("user_appr_page", user_appr_page);
      uiModel.addAttribute("user_appr_size", sizeNo);
      uiModel.addAttribute("user_appr", person_result);
      return "admin/index";
    }
}
