package com.emp.mgmt.dao;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.emp.mgmt.exception.EmployeeNotFoundException;
import com.emp.mgmt.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao{

	Map<String, Employee> employees;
	
	public EmployeeDaoImpl(){
		employees = new ConcurrentHashMap<>();
		Employee teja = new Employee("1","teja", "teja", "Brahma Teja P", "teja@gmail.com", "12/12/1989", "Male", "What is your school name", "NJSP");
		Employee pramod = new Employee("2","pramod", "pramod", "Pramod Raj Bharath", "pramod@gmail.com", "05/06/1987", "Male", "Where did you born", "Vijayawada");
		
		employees.put(teja.getEmpId(), teja);
		employees.put(pramod.getEmpId(), pramod);
	}

	@Override
	public Collection<Employee> getEmployees() {
		return employees.values();
	}

	@Override
	public Employee addEmployee(Employee employee) {
		employee.setEmpId(UUID.randomUUID().toString());
		employees.put(employee.getEmpId(), employee);
		return employee;
	}

	@Override
	public boolean deleteEmployee(String empId) throws EmployeeNotFoundException {
		if(employees.containsKey(empId)){
			employees.remove(empId);
			
			return true;
		}else {
			throw new EmployeeNotFoundException("Employee with id "+empId + " not found");
		}
	}

	@Override
	public Employee getEmployee(String empId) throws EmployeeNotFoundException {
		if(employees.containsKey(empId)){
			return employees.get(empId);
		}else {
			throw new EmployeeNotFoundException("Employee with id "+empId + " not found");
		}
	}

	@Override
	public boolean checkLogin(Employee employee) throws EmployeeNotFoundException {
		boolean canLogin = false;
		for(Entry<String, Employee> emp : employees.entrySet()){
			if(emp.getValue().getUsername().equals(employee.getUsername()) && emp.getValue().getPassword().equals(employee.getPassword())){
				canLogin = true;
				break;
			}
		}
		if(!canLogin){
			throw new EmployeeNotFoundException("Employee not found");
		}
		return canLogin;
	}
	
	
}
