package au.gov.nsw.records.digitalarchives.dashboard.service;

import org.springframework.security.core.context.SecurityContextHolder;

import au.gov.nsw.records.digitalarchives.dashboard.model.Person;

public class UserService {

	public static Person getLoggedinUser(){
		String loggedinName = SecurityContextHolder.getContext().getAuthentication().getName();
	  return Person.findPeopleByEmailEquals(loggedinName).getSingleResult();
	}
}
