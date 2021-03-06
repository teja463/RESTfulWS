package com.emp.mgmt.svc;

import java.util.Collection;

import javax.inject.Inject;

import com.emp.mgmt.dao.EmployeeDao;
import com.emp.mgmt.exception.EmployeeNotFoundException;
import com.emp.mgmt.model.Employee;

public class EmployeeServiceImpl implements EmployeeService{

	@Inject
	EmployeeDao dao;
	
	@Override
	public Collection<Employee> getEmployees() {
		return dao.getEmployees();
	}

	@Override
	public Employee addEmployee(Employee employee) {
		return dao.addEmployee(employee);
	}

	@Override
	public boolean deleteEmployee(String empId) throws EmployeeNotFoundException {
		return dao.deleteEmployee(empId);
	}

	@Override
	public Employee getEmployee(String empId) throws EmployeeNotFoundException {
		return dao.getEmployee(empId);
	}

	@Override
	public boolean checkLogin(Employee employee) throws EmployeeNotFoundException {
		return dao.checkLogin(employee);
	}
	
}
