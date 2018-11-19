package com.emp.mgmt.dao;

import java.util.Collection;

import com.emp.mgmt.exception.EmployeeNotFoundException;
import com.emp.mgmt.model.Employee;

public interface EmployeeDao {
	public Collection<Employee> getEmployees();

	public Employee addEmployee(Employee employee);
	
	public boolean deleteEmployee(String empId) throws EmployeeNotFoundException;
	
	public Employee getEmployee(String empId) throws EmployeeNotFoundException;
	
	public boolean checkLogin(Employee employee) throws EmployeeNotFoundException;
}
