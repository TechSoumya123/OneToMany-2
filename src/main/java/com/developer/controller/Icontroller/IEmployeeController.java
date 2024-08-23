package com.developer.controller.Icontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.developer.model.Employee;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@Validated
@RequestMapping(path = { "/api/v1/employee" })
public interface IEmployeeController {

	@PostMapping(path = { "/create" })
	ResponseEntity<Object> saveEmployee(@RequestBody @Valid final Employee employee);

	@GetMapping(path = { "/byId/{id}" })
	ResponseEntity<Object> getEmployeeById(
			@Min(value = 1, message = "Sorry, Please enter the Positive number, like 1,2,3,4") @PathVariable("id") final Long empId);

	@GetMapping(path = { "/getAll" })
	ResponseEntity<Object> getAllEmployees();

	@PutMapping(path = { "/update/{id}" })
	ResponseEntity<Object> updateEmployee(@PathVariable("id") Long empId, @Valid @RequestBody final Employee employee);

	@DeleteMapping(path = { "/delete/{id}" })
	ResponseEntity<Object> deleteEmployee(@PathVariable("id") final Long empId);

}
