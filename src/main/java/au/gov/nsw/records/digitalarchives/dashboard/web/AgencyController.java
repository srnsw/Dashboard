package au.gov.nsw.records.digitalarchives.dashboard.web;

import au.gov.nsw.records.digitalarchives.dashboard.model.Agency;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/agencies")
@Controller
@RooWebScaffold(path = "agencies", formBackingObject = Agency.class)
public class AgencyController {
}
