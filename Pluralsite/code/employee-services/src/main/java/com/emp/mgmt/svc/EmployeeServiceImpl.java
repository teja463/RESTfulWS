package com.emp.mgmt.svc;

import java.util.Collection;

import javax.inject.Inject;

import com.emp.mgmt.dao.EmployeeDao;
import com.emp.mgmt.model.Employee;

public class EmployeeServiceImpl implements EmployeeService{

	@Inject
	EmployeeDao dao;
	
	@Override
	public Collection<Employee> getEmployees() {
		System.out.println("in service2");
		return dao.getEmployees();
	}

	@Override
	public Employee addEmployee(Employee employee) {
		System.out.println("in service employee" + employee);
		return dao.addEmployee(employee);
	}
	
}
