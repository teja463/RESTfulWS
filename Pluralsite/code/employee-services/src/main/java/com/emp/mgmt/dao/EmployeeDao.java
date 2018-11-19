package com.emp.mgmt.dao;

import java.util.Collection;

import com.emp.mgmt.model.Employee;

public interface EmployeeDao {
	public Collection<Employee> getEmployees();

	public Employee addEmployee(Employee employee);
}
