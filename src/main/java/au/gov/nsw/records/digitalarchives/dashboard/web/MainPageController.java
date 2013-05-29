package au.gov.nsw.records.digitalarchives.dashboard.web;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import au.gov.nsw.records.digitalarchives.dashboard.model.Project;
import au.gov.nsw.records.digitalarchives.dashboard.service.PDFGeneratorService;

import com.itextpdf.text.DocumentException;

@RequestMapping("/")
@Controller
public class MainPageController {

	@RequestMapping(value="/", produces = "text/html")
	public String defaultPage(HttpServletRequest request, Model uiModel) {

		if (request.isUserInRole("ROLE_ARCHIVIST")) {
  		uiModel.addAttribute("projects", Project.findAllProjects());
  		return "home/mainindex";
  	}else if (request.isUserInRole("ROLE_AGENCY")) {
  		try {
				PDFGeneratorService.createPDF();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  		
  		uiModel.addAttribute("projects", Project.findAllProjects());
  		return "home/externalindex";
  	}
		return "home/login";
	}
}
  	
