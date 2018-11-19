package com.emp.mgmt.svc;

import java.util.Collection;

import com.emp.mgmt.model.Employee;

public interface EmployeeService {

	public Collection<Employee> getEmployees();

	public Employee addEmployee(Employee employee);
}
