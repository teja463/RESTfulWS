package com.emp.mgmt;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.grizzly.connector.GrizzlyConnectorProvider;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Ignore;
import org.junit.Test;

import com.emp.mgmt.config.ApplicationConfig;
import com.emp.mgmt.dao.EmployeeDaoImpl;
import com.emp.mgmt.model.Employee;
import com.emp.mgmt.svc.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

public class EmployeeResourceTest extends JerseyTest{

	protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ApplicationConfig();
	}
	
	protected void configureClient(ClientConfig clientConfig){
		JacksonJsonProvider json = new JacksonJsonProvider();
		json.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		clientConfig.register(json);
		clientConfig.connectorProvider(new GrizzlyConnectorProvider());
	}
	
	@Test
	public void getEmployeesTest(){
		Collection<Employee> employees = target("employee").path("getAllEmpDetails").request().get(new GenericType<Collection<Employee>>(){});
		assertEquals(2, employees.size());
	}
	
	private Employee getEmployee(Response response){
		return response.readEntity(Employee.class);
	}
	
	@Test
	public void addEmployeeTest(){
		Employee employee = new Employee("", "chaitu", "chaitu", "SSPC Kumar", "chaitu@google.com", "09/02/1986", "Male", "What is your hobby", "Guitar");
		Entity<Employee> employeeEntity = Entity.entity(employee, MediaType.APPLICATION_JSON);
		Response response = target("employee").path("addEmp").request().put(employeeEntity);
		Employee createdEmployee = getEmployee(response);
		
		assertEquals(200, response.getStatus());
		assertEquals("chaitu", createdEmployee.getUsername());
		
		Collection<Employee> employees = target("employee").path("getAllEmpDetails").request().get(new GenericType<Collection<Employee>>(){});
		assertEquals(3, employees.size());
	}
	
	@Test
	public void deleteEmployeeTest(){
		Response response = target("employee").path("deleteEmp").path("1").request().put(Entity.entity("Sample", MediaType.TEXT_PLAIN));
		String message = response.readEntity(String.class);
		
		assertEquals(200, response.getStatus());
		assertEquals("Success", message);
	}
	
	@Test
	public void deleteEmployeeNotFoundExceptionTest(){
		Response response = target("employee").path("deleteEmp").path("122").request().put(Entity.entity("Sample", MediaType.TEXT_PLAIN));
//		String message = response.readEntity(String.class);
		assertEquals(404, response.getStatus());
	}
	
	@Test
	public void getEmployeeDetailsTest(){
		Response response = target("employee").path("getByEmpId").path("2").request().get();
		Employee employee = response.readEntity(Employee.class);
		
		assertEquals(200, response.getStatus());
		assertEquals("pramod", employee.getUsername());
		
	}
	
	@Test
	public void getEmployeeDetailsNotFoundTest(){
		Response response = target("employee").path("getByEmpId").path("44").request().get();
		assertEquals(404, response.getStatus());
		
	}
}
