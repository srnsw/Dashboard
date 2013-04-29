package au.gov.nsw.records.digitalarchives.dashboard.web;

import au.gov.nsw.records.digitalarchives.dashboard.model.Page;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/pages")
@Controller
@RooWebScaffold(path = "pages", formBackingObject = Page.class)
public class PageController {

	@RequestMapping(value = "/projectplan", method = RequestMethod.GET, produces = "text/html")
  public String projectplan(Model uiModel) {
		// TODO validate role
		Page page = Page.findPage(1L);
    //page.setContent("project plan");
    //page.setTitle("Project Plan Template");
    populateEditForm(uiModel, page);
    return "pages/update";
  }
	
	@RequestMapping(value = "/migrationplan", method = RequestMethod.GET, produces = "text/html")
  public String migrationplan(Model uiModel) {
		// TODO validate role
		Page page = Page.findPage(2L);
    //page.setContent("migration plan content");
    //page.setTitle("Migration Plan Template");
    populateEditForm(uiModel, page);
    return "pages/update";
  }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Page paged = Page.findPage(id);
        paged.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/pages";
    }

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Page page, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, page);
            return "pages/create";
        }
        uiModel.asMap().clear();
        page.persist();
        return "redirect:/pages/" + encodeUrlPathSegment(page.getId().toString(), httpServletRequest);
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Page page, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, page);
            return "pages/update";
        }
        uiModel.asMap().clear();
        page.merge();
        return "redirect:/pages/" + encodeUrlPathSegment(page.getId().toString(), httpServletRequest);
    }
	
	

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel, @RequestParam(value = "master", required = false) String master) {
		Page page = null;
		if (master!=null){
    	if (master.equals("projectplan")){
    		// TODO define master copy of aaa
    		page = Page.findPage(1L);
    	}else if (master.equals("migrationplan")){
    		// TODO define master copy of bbb
    		page = Page.findPage(2L);
    	}
    }else{
    	page = new Page();
    }
    populateEditForm(uiModel, page);
    return "pages/create";
    }
}
