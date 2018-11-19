package com.emp.mgmt.svc;

import java.util.Collection;

import com.emp.mgmt.exception.EmployeeNotFoundException;
import com.emp.mgmt.model.Employee;

public interface EmployeeService {

	public Collection<Employee> getEmployees();

	public Employee addEmployee(Employee employee);
	
	public boolean deleteEmployee(String empId)  throws EmployeeNotFoundException;
	
	public Employee getEmployee(String empId) throws EmployeeNotFoundException;
	
	public boolean checkLogin(Employee employee) throws EmployeeNotFoundException;
}
