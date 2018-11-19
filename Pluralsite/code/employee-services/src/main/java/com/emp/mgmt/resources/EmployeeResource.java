package com.emp.mgmt.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ManagedAsync;

import com.emp.mgmt.model.Employee;
import com.emp.mgmt.svc.EmployeeService;

@Path("/employee")
public class EmployeeResource {

	@Inject EmployeeService svc;
	
	@GET
	@Path("/getAllEmpDetails")
	@Produces(MediaType.APPLICATION_JSON)
	@ManagedAsync
	public void getAllEmpDetails(@Suspended AsyncResponse response){
		System.out.println("in resource");
		response.resume(svc.getEmployees());
	}
	
	@PUT
	@Path("/addEmp")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ManagedAsync
	public void addEmployee(Employee employee, @Suspended AsyncResponse response){
		System.out.println(employee);
		Employee addEmployee = svc.addEmployee(employee);
		System.out.println("added employee" + addEmployee);
		response.resume(addEmployee);
	}
}
