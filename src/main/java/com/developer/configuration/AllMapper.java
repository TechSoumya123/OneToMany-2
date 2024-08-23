package com.developer.configuration;

import org.springframework.stereotype.Component;

import com.developer.model.Address;
import com.developer.model.Employee;

@Component
public class AllMapper {

	public static Employee existstingToUpdate(Employee existingEmployee, Employee employee) {
		return existingEmployee.setName(employee.getName());
	}

	public static Address existingAddressToUpdated(Address existingAddress, Address address) {
		return existingAddress.setCity(address.getCity()).setDistrict(address.getDistrict())
				.setState(address.getState()).setZipCode(address.getZipCode());
	}

}
