package com.developer.service.Iservice;

import java.util.List;

import com.developer.model.Address;
import com.developer.model.Employee;

public interface AddressService {

	Address createNewAddress(Long empId, Address address);

	List<Address> getAllAddress();

	Address getAddressById(Long addressId);

	Address updateAddress(Long addressId, Address address);

	void deleteAddress(Long addressId);

	void addEmployeeToAddress(Long addressId, Long empId);

	void removeEmployeeFromAddress(Long addressId);

	void updateEmployeeFromAddressSide(Long addressId, Employee employee);

}
