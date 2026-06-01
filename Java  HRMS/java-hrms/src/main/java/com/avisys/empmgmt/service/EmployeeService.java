package com.avisys.empmgmt.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.avisys.empmgmt.dto.EmployeeDto;

import jakarta.validation.Valid;


@Service
public interface EmployeeService {

	public List<EmployeeDto> getAllEmployee();

	public EmployeeDto createEmployee(@Valid EmployeeDto employee);

	Page<EmployeeDto> searchEmployee(Pageable pageable, String keyword);

	public EmployeeDto updateEmployee(@Valid EmployeeDto employee);

	public EmployeeDto getByEmployee(Long employeeId);

	public String deleteEmployee(Long employeeId,String updatedBy);
	
}
