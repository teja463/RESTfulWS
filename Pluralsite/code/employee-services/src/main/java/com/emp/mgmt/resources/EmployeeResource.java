package com.emp.mgmt.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ManagedAsync;

import com.emp.mgmt.exception.EmployeeNotFoundException;
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
		response.resume(svc.getEmployees());
	}
	
	@PUT
	@Path("/addEmp")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ManagedAsync
	public void addEmployee(Employee employee, @Suspended AsyncResponse response){
		response.resume(svc.addEmployee(employee));
	}
	
	@PUT
	@Path("deleteEmp/{empId}")
	@ManagedAsync
	public void deleteEmployee(@PathParam("empId") String empId, @Suspended AsyncResponse response){
		try {
			boolean deleteEmployee = svc.deleteEmployee(empId);
			if(deleteEmployee)
				response.resume("Success");
		} catch (EmployeeNotFoundException e) {
			response.resume(e);
		}
	}
	
	@GET
	@Path("getByEmpId/{empId}")
	@Produces(MediaType.APPLICATION_JSON)
	@ManagedAsync
	public void getEmployeeDetails(@PathParam("empId") String empId, @Suspended AsyncResponse response){
		try {
			response.resume(svc.getEmployee(empId));
		} catch (EmployeeNotFoundException e) {
			response.resume(e);
		}
	}
}
