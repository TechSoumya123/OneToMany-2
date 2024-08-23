package com.developer.service.Iservice;

import java.util.List;
import java.util.Optional;

import com.developer.model.Employee;

public interface EmployeeService {

	Employee createNewEmployee(Employee employee);

	Employee getEmployeeById(Long empId);

	Optional<Employee> getEmployeeOrMightBeNull(Long empId);
	
	List<Employee> getAllEmployees();

	Employee updateEmployee(Long empId, Employee employee);

	void deleteEmployee(Long empId);

}
