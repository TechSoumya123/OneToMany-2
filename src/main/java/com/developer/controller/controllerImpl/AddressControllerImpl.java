package com.developer.controller.controllerImpl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.developer.controller.Icontroller.IAddressController;
import com.developer.model.Address;
import com.developer.model.Employee;
import com.developer.service.Iservice.AddressService;

import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AddressControllerImpl implements IAddressController {

	private final AddressService addressService;

	@Override
	public ResponseEntity<Object> createNewAddress(Long empId, Address address) {
		try {
			Address addressSaved = addressService.createNewAddress(empId, address);
			return ResponseEntity.status(HttpStatus.CREATED).body(addressSaved);
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

	@Override
	public ResponseEntity<Object> getAllAddress() {
		try {
			List<Address> listOfAddress = addressService.getAllAddress();
			return listOfAddress.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listOfAddress);
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

	@Override
	public ResponseEntity<Object> getAddressById(Long addressId) {
		try {
			Address address = addressService.getAddressById(addressId);
			return ResponseEntity.ok(address);
		} catch (NoResultException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

	@Override
	public ResponseEntity<Object> updateAddress(Long addressId, Address address) {
		try {
			Address updateAddress = addressService.updateAddress(addressId, address);
			return ResponseEntity.ok(updateAddress);
		} catch (NoResultException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> deleteAddress(Long addressId) {
		try {
			addressService.deleteAddress(addressId);
			return ResponseEntity.ok().build();
		} catch (NoResultException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

	@Override
	public ResponseEntity<Object> addEmployeeToAddress(Long addressId, Long empId) {
		try {
			addressService.addEmployeeToAddress(addressId, empId);
			return ResponseEntity.ok("Employee successfully added to this address");
		} catch (NoResultException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

	@Override
	public ResponseEntity<Object> removeEmployeeFromAddress(Long addressId) {
		try {
			addressService.removeEmployeeFromAddress(addressId);
			return ResponseEntity.ok("ok");
		} catch (NoResultException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

	@Override
	public ResponseEntity<Object> updateEmployeeFromAddressSide(Long addressId, Employee employee) {
		try {
			addressService.updateEmployeeFromAddressSide(addressId, employee);
			return ResponseEntity.ok("Updated");
		} catch (NoResultException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

}
