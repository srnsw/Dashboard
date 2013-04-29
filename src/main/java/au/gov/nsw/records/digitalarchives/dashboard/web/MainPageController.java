package au.gov.nsw.records.digitalarchives.dashboard.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class MainPageController {

	@RequestMapping(value="/", produces = "text/html")
	public String defaultPage(Model uiModel) {

		return "home/login";
	}
}
  	
