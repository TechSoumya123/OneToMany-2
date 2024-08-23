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

import com.developer.model.Address;
import com.developer.model.Employee;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@Validated
@RequestMapping(path = { "/api/v1/address" })
public interface IAddressController {

	@PostMapping(path = { "/create/{id}" })
	ResponseEntity<Object> createNewAddress(
			@PathVariable("id") @Min(value = 1, message = "Employee id must be grater than 0") final Long empId,
			@RequestBody @Valid Address address);

	@GetMapping(path = { "/getAll" })
	ResponseEntity<Object> getAllAddress();

	@GetMapping(path = { "/getBy/{id}" })
	ResponseEntity<Object> getAddressById(
			@PathVariable("id") @Min(value = 1, message = "Addres must be grater than 0") final Long addressId);

	@PutMapping(path = { "/update/{id}" })
	ResponseEntity<Object> updateAddress(
			@PathVariable("id") @Min(value = 1, message = "Addres must be grater than 0") final Long addressId,
			@RequestBody Address address);

	@DeleteMapping(path = { "/delete/{id}" })
	ResponseEntity<?> deleteAddress(
			@PathVariable("id") @Min(value = 1, message = "Addres must be grater than 0") final Long addressId);

	@PostMapping(path = { "/addETA/{addressId}/{empId}" })
	ResponseEntity<Object> addEmployeeToAddress(@PathVariable("addressId") final Long addressId,
			@PathVariable("empId") final Long empId);

	@PostMapping(path = { "/removeEFA/{addressId}" })
	ResponseEntity<Object> removeEmployeeFromAddress(@PathVariable("addressId") final Long addressId);

	@PutMapping(path = { "/updateEmpFromAddressSide/{addressId}" })
	ResponseEntity<Object> updateEmployeeFromAddressSide(@PathVariable("addressId") final Long addressId,
			@RequestBody Employee employee);
}
