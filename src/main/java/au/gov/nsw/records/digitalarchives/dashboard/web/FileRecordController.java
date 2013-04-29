package au.gov.nsw.records.digitalarchives.dashboard.web;

import au.gov.nsw.records.digitalarchives.dashboard.model.FileRecord;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/filerecords")
@Controller
@RooWebScaffold(path = "filerecords", formBackingObject = FileRecord.class)
public class FileRecordController {
}
