package au.gov.nsw.records.digitalarchives.dashboard.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import au.gov.nsw.records.digitalarchives.dashboard.bean.JTableResponse;
import au.gov.nsw.records.digitalarchives.dashboard.bean.JTableResponse.Status;
import au.gov.nsw.records.digitalarchives.dashboard.model.Project;
import au.gov.nsw.records.digitalarchives.dashboard.service.JsonService;
import au.gov.nsw.records.digitalarchives.dashboard.service.PDFGeneratorService;

import com.itextpdf.text.DocumentException;

@RequestMapping("/workspace")
@Controller
public class HomeController {

    @RequestMapping(method = RequestMethod.POST, value = "{id}")
    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    @RequestMapping
    public String index(HttpServletRequest request) {
    	
    	if (request.isUserInRole("ROLE_ARCHIVIST")) {
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
    		return "home/externalindex";
    	}
       return "redirect:/";
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/upload", method =  RequestMethod.POST)
  	public void upload(MultipartFile content, HttpServletRequest request){
    	
    	if (!(request instanceof MultipartHttpServletRequest)) {
        //error(resp, "Invalid request (multipart request expected)");
        //lreturn null;
    		System.out.println("not multipart request!!");
    	}
    	Map<String, MultipartFile> files = ((MultipartHttpServletRequest)request).getFileMap();
    	for (String f:files.keySet()){
    		System.out.println("files:" + f);
    		MultipartFile mp = files.get(f);
    		System.out.println("file_name:" + mp.getOriginalFilename());
    		System.out.println("file_size:" + mp.getSize());
    	}
    	
    	System.out.println("Uploaded:" + content==null?"":content.getOriginalFilename());
  	}
    
    @RequestMapping(value = "/download", method =  RequestMethod.GET)
  	public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	response.setHeader("Content-Disposition", "attachment; filename=\"" + "_filename.ext_" + "\"");
    	FileInputStream in = new FileInputStream("foo.txt");
    	if (in!=null){
    		OutputStream out = response.getOutputStream();
    		IOUtils.copy(in, out);	
    		response.flushBuffer();
    	}
  	}
    
  	@RequestMapping(method = RequestMethod.POST, value = "/showprojects", produces = "application/json")
    public @ResponseBody String showprojects( Model uiModel) {
  			
  			return JsonService.toJson(new JTableResponse(Status.OK, Project.findAllProjects()));
  	}
  	
  	@RequestMapping(method = RequestMethod.POST, value = "/showprojects2", produces = "application/json")
    public @ResponseBody String showprojectsex( Model uiModel) {
  			List<Project> results = new ArrayList<Project>();
  			results.add(Project.findProject(Long.valueOf(1)));
				results.add(Project.findProject(Long.valueOf(2)));
  			return JsonService.toJson(new JTableResponse(Status.OK, results));
  	}
  	
}
