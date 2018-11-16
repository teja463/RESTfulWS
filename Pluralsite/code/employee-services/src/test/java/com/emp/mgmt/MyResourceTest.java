package com.emp.mgmt;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import com.emp.mgmt.config.ApplicationConfig;
import com.emp.mgmt.dao.EmployeeDao;
import com.emp.mgmt.dao.EmployeeDaoImpl;
import com.emp.mgmt.svc.EmployeeService;
import com.emp.mgmt.svc.EmployeeServiceImpl;


public class MyResourceTest extends JerseyTest{

	protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		final EmployeeService svc = new EmployeeServiceImpl();
    	final EmployeeDao dao = new EmployeeDaoImpl();
		return new ApplicationConfig(dao, svc);
	}
	
	@Test
    public void testGetIt() {
        String responseMsg = target("myresource").request().get(String.class);
        assertEquals("Got it!", responseMsg);
    }
}
