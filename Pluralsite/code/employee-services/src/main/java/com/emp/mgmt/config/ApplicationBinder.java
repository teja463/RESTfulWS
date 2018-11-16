package com.emp.mgmt.config;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.emp.mgmt.dao.EmployeeDao;
import com.emp.mgmt.dao.EmployeeDaoImpl;
import com.emp.mgmt.svc.EmployeeService;
import com.emp.mgmt.svc.EmployeeServiceImpl;

public class ApplicationBinder extends AbstractBinder{

	@Override
	protected void configure() {
		bind(EmployeeServiceImpl.class).to(EmployeeService.class);
		bind(EmployeeDaoImpl.class).to(EmployeeDao.class);
	}

}
