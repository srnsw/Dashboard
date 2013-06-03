package au.gov.nsw.records.digitalarchives.dashboard.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.RandomStringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import au.gov.nsw.records.digitalarchives.dashboard.bean.AutocompleteResponse;
import au.gov.nsw.records.digitalarchives.dashboard.bean.JTableResponse;
import au.gov.nsw.records.digitalarchives.dashboard.bean.JTableResponse.Status;
import au.gov.nsw.records.digitalarchives.dashboard.email.NotificationService;
import au.gov.nsw.records.digitalarchives.dashboard.model.Person;
import au.gov.nsw.records.digitalarchives.dashboard.service.JsonService;
import au.gov.nsw.records.digitalarchives.dashboard.service.UserService;

@RequestMapping("/members")
@Controller
@RooWebScaffold(path = "members", formBackingObject = Person.class)
public class MemberController {
	
	@Autowired
	private NotificationService notificationService;
	
	@RequestMapping(value = "/{id}/approve",method = RequestMethod.GET, produces = "text/html")
  public String approveForm(@PathVariable("id") Long id, Model uiModel) {
     
		 Person person = Person.findPerson(id);
		 if (person!=null){
			 populateEditForm(uiModel, person);
			 return "members/update";
		 }

		 uiModel.asMap().clear();

      return "projects/update";
  }
	
	//@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String create(@Valid Person people, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, people);
			return "members/create";
		}
		
		//password generation
		String passwd = RandomStringUtils.randomAlphanumeric(8);
		people.setPassword(passwd);
		
		//password encryption
		MessageDigest md;
		try {
			uiModel.asMap().clear();
			md = MessageDigest.getInstance("md5");
			md.update(people.getPassword().getBytes("UTF-8"));
			byte[] result =  md.digest();
			people.setPassword(Hex.encodeHexString(result));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		people.persist();
		
		System.out.println("passwd:" + passwd);
		
		// mail to the user
		notificationService.sendMessage("wisanu.promthong@records.nsw.gov.au", "test mail passwd is:" + passwd);
		// mail to administrator
		notificationService.sendMessage("wisanu.promthong@records.nsw.gov.au", "test mail passwd is:" + passwd);
		
		// TODO redirect to register completed with text
		return "members/post_create";
		//return "redirect:/members/" + encodeUrlPathSegment(people.getId().toString(), httpServletRequest);
	}
	
	@RequestMapping(value = "/myprofile", method = RequestMethod.GET, produces = "text/html")
  public String migrationplan(Model uiModel) {
		Person current = UserService.getLoggedinUser();
		if (current!=null){
	    uiModel.addAttribute("person", current);
	    uiModel.addAttribute("itemId", current.getId());
		}
    return "members/show";
  }
	
	@RequestMapping(method = RequestMethod.GET, value="/retrieve")
	public String retrievelogin(){
		return "members/retrieve";
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/getagencies")
	public @ResponseBody String getAgencies(@RequestParam String term){
		ObjectMapper om = new ObjectMapper();
		List<AutocompleteResponse> listResutls = new ArrayList<AutocompleteResponse>();
		//String htmlResult = "";
    om.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    om.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    String json = "";
		try {

			URL urlr = new URL("http://api.records.nsw.gov.au/search.json?entities=Agency&q=" + term);
			HttpURLConnection conn = (HttpURLConnection) urlr.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String line;
			while ((line = br.readLine()) != null) {
				json = json + line;
			}
			conn.disconnect();
			
			JsonNode rootNode = om.readValue(json, JsonNode.class);
			JsonNode nameNode = rootNode.path("StateRecordsSearch").path("Results");
			Iterator<JsonNode> js = nameNode.getElements();
			while(js.hasNext()){
				JsonNode node =  js.next().path("Result");
				listResutls.add(new AutocompleteResponse(node.get("title").getTextValue().replaceAll("\"", ""), node.get("href").getTextValue().replaceAll("\"", "").replaceAll("http://api.records.nsw.gov.au/agencies/", "")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(JsonService.toJson(listResutls));
		return JsonService.toJson(listResutls);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public @ResponseBody String listMembers(Model uiModel) {
     return JsonService.toJson(new JTableResponse(Status.OK, Person.findAllPeople()));
  }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", defaultValue="1", required = false) Integer page, @RequestParam(value = "size", defaultValue="10",  required = false) Integer size, Model uiModel) {

            List<Person> persons = Person.findPeopleByApprovedNot(false).getResultList();
            float nrOfPages = (float) persons.size() / size;
            persons = new ArrayList<Person>(persons.subList(Math.max((page-1)*size, 0), Math.min(page*size, persons.size())));
            uiModel.addAttribute("user_maxpage", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
            uiModel.addAttribute("user_page", page);
            uiModel.addAttribute("user_size", size);
            uiModel.addAttribute("user", persons);

        return "members/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Person person, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, person);
            return "members/update";
        }
        
        uiModel.asMap().clear();
        
        // TODO password comparison
        //password encryption
    		MessageDigest md;
    		try {
    			uiModel.asMap().clear();
    			md = MessageDigest.getInstance("md5");
    			md.update(person.getPassword().getBytes("UTF-8"));
    			byte[] result =  md.digest();
    			person.setPassword(Hex.encodeHexString(result));
    		} catch (NoSuchAlgorithmException e) {
    			e.printStackTrace();
    		} catch (UnsupportedEncodingException e) {
    			e.printStackTrace();
    		}
    		
        person.merge();
        return "redirect:/members/" + encodeUrlPathSegment(person.getId().toString(), httpServletRequest);
    }

	//@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
				Person person = Person.findPerson(id);
//				
//				if (person.getEmail().equals(UserService.getLoggedinUser().getEmail())){
//					uiModel.addAttribute("ismyuser", "true");
//				}else{
//					
//				}
				
        uiModel.addAttribute("person", Person.findPerson(id));
        uiModel.addAttribute("itemId", id);
        return "members/admin_show";
    }
}
