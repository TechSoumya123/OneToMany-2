package com.developer.service.serviceImpl;

import static com.developer.configuration.AllMapper.existstingToUpdate;
import static org.springframework.util.ObjectUtils.isEmpty;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.developer.model.Employee;
import com.developer.repository.EmployeeRepository;
import com.developer.service.Iservice.EmployeeService;

import jakarta.persistence.NoResultException;

@Validated
@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee createNewEmployee(Employee employee) {
		Employee saveEmployee = employeeRepository.save(employee);
		return saveEmployee;
	}

	@Override
	public Employee getEmployeeById(Long empId) {
		return employeeRepository.findById(empId)
				.orElseThrow(() -> new NoResultException("Sorry, Employee could not be found!"));
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Transactional
	@Override
	public Employee updateEmployee(Long empId, Employee employee) {
		return employeeRepository.findById(empId)
				.map(existingEmployee -> existstingToUpdate(existingEmployee, employee))
				.orElseThrow(() -> new NoResultException("Sorry, Employee could not be found!"));
	}

	@Transactional
	@Override
	public void deleteEmployee(Long empId) {
		employeeRepository.findById(empId).ifPresentOrElse(emp -> {
			if (isEmpty(emp.getAddressList())) {
				employeeRepository.delete(emp);
			} else {
				emp.getAddressList().stream().forEach(address -> address.setEmployee(null));
				emp.setAddressList(null);
				employeeRepository.delete(emp);
			}
		}, () -> {
			throw new NoResultException("Sorry, Employee could not be found!");
		});
	}

	@Override
	public Optional<Employee> getEmployeeOrMightBeNull(Long empId) {
		return Optional.ofNullable(employeeRepository.findById(empId).orElse(null));
	}

}
