package com.emp.mgmt.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import com.emp.mgmt.dao.EmployeeDao;
import com.emp.mgmt.svc.EmployeeService;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fasterxml.jackson.jaxrs.xml.JacksonXMLProvider;

public class ApplicationConfig extends ResourceConfig{

	JacksonJsonProvider json = new JacksonJsonProvider().
			configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).
			configure(SerializationFeature.INDENT_OUTPUT,true);
	
	JacksonXMLProvider xml = new JacksonXMLProvider().
			configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).
			configure(SerializationFeature.INDENT_OUTPUT,true);
	
	public ApplicationConfig(){
		
		packages("com.emp.mgmt").
			register(json).
			register(xml).
			register(new ApplicationBinder());
		
		property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
	}
}
