package com.developer.service.serviceImpl;

import static com.developer.configuration.AllMapper.existingAddressToUpdated;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.developer.model.Address;
import com.developer.model.Employee;
import com.developer.repository.AddressRepository;
import com.developer.service.Iservice.AddressService;
import com.developer.service.Iservice.EmployeeService;

import jakarta.persistence.NoResultException;

@Service
public class AddressServiceImpl implements AddressService {

	private final AddressRepository addressRepository;
	private final EmployeeService employeeService;

	public AddressServiceImpl(AddressRepository addressRepository, EmployeeService employeeService) {
		this.addressRepository = addressRepository;
		this.employeeService = employeeService;
	}

	@Transactional
	@Override
	public Address createNewAddress(Long empId, Address address) {
		var addressObj = new Address().setCity(address.getCity()).setDistrict(address.getDistrict())
				.setState(address.getState()).setZipCode(address.getZipCode());
		Optional<Employee> optionalEmployee = employeeService.getEmployeeOrMightBeNull(empId);
		if (isNull(optionalEmployee)) {
			return addressRepository.save(addressObj);
		} else {
			optionalEmployee.ifPresent(addressObj::setEmployee);
			addressRepository.save(addressObj);
			return addressObj;
		}
	}

	@Override
	public List<Address> getAllAddress() {
		return addressRepository.findAll();
	}

	@Override
	public Address getAddressById(Long addressId) {
		return addressRepository.findById(addressId)
				.orElseThrow(() -> new NoResultException("Sorry, Address could not be found"));
	}

	@Transactional
	@Override
	public Address updateAddress(Long addressId, Address address) {
		return addressRepository.findById(addressId)
				.map(existingAddress -> existingAddressToUpdated(existingAddress, address))
				.orElseThrow(() -> new NoResultException("Sorry, Address could not be found"));
	}

	@Override
	public void deleteAddress(Long addressId) {
		addressRepository.findById(addressId).ifPresentOrElse(add -> addressRepository.deleteById(addressId),
				() -> new NoResultException("Sorry, Address could not be found"));
	}

	@Transactional
	@Override
	public void addEmployeeToAddress(Long addressId, Long empId) {
		addressRepository.findById(addressId).ifPresentOrElse(address -> {
			if (isNull(address.getEmployee())) {
				address.setEmployee(employeeService.getEmployeeById(empId));
			} else {
				throw new NoResultException("Address already contains a employee");
			}
		}, () -> {
			throw new NoResultException("Address Not found");
		});
	}

	@Transactional
	@Override
	public void removeEmployeeFromAddress(Long addressId) {
		addressRepository.findById(addressId).ifPresentOrElse(address -> {
			if (nonNull(address.getEmployee())) {
				address.setEmployee(null);
			} else {
				throw new NoResultException("Sorry, Address doesn't contain any employee");
			}
		}, () -> {
			throw new NoResultException("Sorry, Address not found");
		});
	}

	@Transactional
	@Override
	public void updateEmployeeFromAddressSide(Long addressId, Employee employee) {
		addressRepository.findById(addressId).ifPresentOrElse((address -> {
			if (isNull(address.getEmployee())) {
				throw new NoResultException("Address doesn,t contain any employee");
			} else {
				address.getEmployee().setName(employee.getName());
			}
		}), () -> {
			throw new NoResultException("Address not found");
		});
	}

}
