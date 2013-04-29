package au.gov.nsw.records.digitalarchives.dashboard.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import au.gov.nsw.records.digitalarchives.dashboard.bean.FileStore;
import au.gov.nsw.records.digitalarchives.dashboard.model.Upload;
import au.gov.nsw.records.digitalarchives.dashboard.service.MockupHolder;

import org.springframework.http.HttpStatus;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RequestMapping("/uploads")
@Controller
@RooWebScaffold(path = "uploads", formBackingObject = Upload.class)
public class UploadController {
	
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value = "/create", method =  RequestMethod.POST)
	public void upload(MultipartFile content, HttpServletRequest request) throws Exception{
  	
  	try{
  	if (!(request instanceof MultipartHttpServletRequest)) {
      //error(resp, "Invalid request (multipart request expected)");
      //return null;
  		System.out.println("not multipart request!!");
  	}
  	Map<String, MultipartFile> files = ((MultipartHttpServletRequest)request).getFileMap();
  	for (String f:files.keySet()){
  		System.out.println("files:" + f);
  		MultipartFile mp = files.get(f);
  		System.out.println("file_name:" + mp.getOriginalFilename());
  		System.out.println("file_size:" + mp.getSize());
  		
  		FileStore fs = new FileStore(mp.getOriginalFilename(), mp.getSize());
  		MockupHolder.addFileStore(fs);
  		
  		System.out.println("Size:" + fs.getFileSize());
  	}
  	
  	//System.out.println("Uploaded:" + content==null?"":content.getOriginalFilename());
  	}catch(Exception e){
  		e.printStackTrace();
  		throw e;
  	}
	}
  

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("uploads", Upload.findUploadEntries(firstResult, sizeNo));
            float nrOfPages = (float) Upload.countUploads() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("uploads", Upload.findAllUploads());
        }
        
        uiModel.addAttribute("filelist", MockupHolder.getFileList());
        
        return "uploads/list";
    }
}
