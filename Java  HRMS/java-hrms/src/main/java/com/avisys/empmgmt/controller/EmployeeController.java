package com.avisys.empmgmt.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.avisys.empmgmt.dto.EmployeeDto;
import com.avisys.empmgmt.service.EmployeeService;
import com.avisys.empmgmt.util.ApiResponse;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class EmployeeController {	
	
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/get-all")
	public ResponseEntity<?> getAllEmployee(){
		List<EmployeeDto> allEmployeeInfo=this.employeeService.getAllEmployee();
		return new ResponseEntity<List<EmployeeDto>>(allEmployeeInfo, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<EmployeeDto> addEmployee(@Valid @RequestBody EmployeeDto employee){
		EmployeeDto createEmployee = this.employeeService.createEmployee(employee);
		return new ResponseEntity<EmployeeDto>(createEmployee, HttpStatus.OK);
	}
	
	@GetMapping("/{employee-id}")
	public ResponseEntity<EmployeeDto> getByEmployeeCode(@PathVariable("employee-id") Long employeeId) {
		EmployeeDto getEmployee = this.employeeService.getByEmployee(employeeId);
		return new ResponseEntity<EmployeeDto>(getEmployee, HttpStatus.OK);
	}
	
	@DeleteMapping("/{employee-id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("employee-id") Long employeeId,@RequestParam(value = "updatedBy") String updatedBy) {
		String deletedEmployee = this.employeeService.deleteEmployee(employeeId,updatedBy);
		return new ResponseEntity<>(new ApiResponse(deletedEmployee,LocalDateTime.now()), HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<EmployeeDto> updateEmployee(@Valid @RequestBody EmployeeDto employee) {
		EmployeeDto updateEmployee = this.employeeService.updateEmployee(employee);
		return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<Page<EmployeeDto>> searchEmployee(@PageableDefault Pageable pageable,
			@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword) {
		Page<EmployeeDto> result = this.employeeService.searchEmployee(pageable, keyword);
		return new ResponseEntity<Page<EmployeeDto>>(result, HttpStatus.OK);
	}
	
}
