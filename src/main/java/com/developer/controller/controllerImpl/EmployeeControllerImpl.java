package com.developer.controller.controllerImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.developer.controller.Icontroller.IEmployeeController;
import com.developer.model.Employee;
import com.developer.service.Iservice.EmployeeService;

import jakarta.persistence.NoResultException;

@RestController
public class EmployeeControllerImpl implements IEmployeeController {

	private final EmployeeService employeeService;

	final static Logger logger = LoggerFactory.getLogger(EmployeeControllerImpl.class);

	public EmployeeControllerImpl(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Override
	public ResponseEntity<Object> saveEmployee(Employee employee) {
		try {
			Employee isSaved = employeeService.createNewEmployee(employee);
			return ResponseEntity.status(HttpStatus.CREATED).body(isSaved);
		} catch (Exception exception) {
			logger.error("\n Exception occured during save data into the database \n {}", exception.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

	@Override
	public ResponseEntity<Object> getEmployeeById(Long empId) {
		try {
			Employee employee = employeeService.getEmployeeById(empId);
			return ResponseEntity.status(HttpStatus.OK).body(employee);
		} catch (NoResultException exception) {
			logger.error("\n Exception occured during save data into the database \n {} ",
					exception.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		} catch (Exception exception) {
			logger.error("\n Exception occured during save data into the database \n  {} ",
					exception.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

	@Override
	public ResponseEntity<Object> getAllEmployees() {
		try {
			List<Employee> listOfEmployees = employeeService.getAllEmployees();
			return listOfEmployees.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listOfEmployees);
		} catch (Exception exception) {
			logger.error("\n Exception occured during fetch All employees \n {} ", exception.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

	@Override
	public ResponseEntity<Object> updateEmployee(Long empId, Employee employee) {
		try {
			Employee updateEmployee = employeeService.updateEmployee(empId, employee);
			return ResponseEntity.ok(updateEmployee);
		} catch (NoResultException exception) {
			logger.error("\n Exception occured during updateEmployee \n {} ", exception.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		} catch (Exception exception) {
			logger.error("\n Exception occured during updateEmployee \n {} ", exception.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

	@Override
	public ResponseEntity<Object> deleteEmployee(Long empId) {
		try {
			employeeService.deleteEmployee(empId);
			return ResponseEntity.ok("Employee deleted successfully");
		} catch (NoResultException exception) {
			logger.error("\n Exception occured during  deleteEmployee \n {}", exception);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		} catch (Exception exception) {
			logger.error("\n Exception occured during  deleteEmployee \n  {} ", exception.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

}
