package au.gov.nsw.records.digitalarchives.dashboard.web;

import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.roo.addon.web.mvc.controller.converter.RooConversionService;

import au.gov.nsw.records.digitalarchives.dashboard.model.Person;
import au.gov.nsw.records.digitalarchives.dashboard.model.Stakeholder;
import au.gov.nsw.records.digitalarchives.dashboard.model.Upload;

/**
 * A central place to register application converters and formatters. 
 */
@RooConversionService
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
		// Register application converters and formatters
	}
	
	 public Converter<Stakeholder, String> getStakeholderToStringConverter() {
     return new org.springframework.core.convert.converter.Converter<au.gov.nsw.records.digitalarchives.dashboard.model.Stakeholder, java.lang.String>() {
    	 @Override 
    	 public String convert(Stakeholder stakeholder) {
             return stakeholder.getName();
        }
     };
 }

	public Converter<Person, String> getPersonToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<au.gov.nsw.records.digitalarchives.dashboard.model.Person, java.lang.String>() {
            public String convert(Person person) {
                return new StringBuilder().append(person.getName()).append(" ").append(person.getLastName()).toString();
            }
        };
    }
}
