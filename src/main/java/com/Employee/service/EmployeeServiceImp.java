package com.Employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Employee.entity.Employee;
import com.Employee.exception.ResourceNotFoundException;

@Service
public class EmployeeServiceImp implements IEmployeeService {
	
	@Autowired
	IEmployeeRepo employeeRepo;

	@Override
	public Integer saveEmployee(Employee employee) {
		
		Employee saveEmployee = employeeRepo.save(employee);
		
		return saveEmployee.getId() ;
	}

	@Override
	public List<Employee> getAllEmployess() {
		
		return employeeRepo.findAll();
	}

	@Override
	public Optional<Employee> getEmployee(Integer id) {
		
		return employeeRepo.findById(id);
	}

	@Override
	public void deleteEmployee(Integer id) {
	  employeeRepo.deleteById(id);
		
	}

	@Override
	public Employee updateEmployee(Employee emplpyee, Integer id) {
		// we will check if an employee exits from a given id or not
		
		Employee existingEmployee = employeeRepo.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Employee", "id", id));
		
		existingEmployee.setName(emplpyee.getName());
		
		existingEmployee.setEmp_position(emplpyee.getEmp_position());
		
		existingEmployee.setSalary(emplpyee.getSalary());
		
		existingEmployee.setQualification(emplpyee.getQualification());
		
		employeeRepo.save(existingEmployee);
		
		return existingEmployee;
	}

}
