package com.avisys.empmgmt.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.avisys.empmgmt.dto.EmployeeDto;
import com.avisys.empmgmt.dto.MailData;
import com.avisys.empmgmt.entity.Division;
import com.avisys.empmgmt.entity.Employee;
import com.avisys.empmgmt.exception.DivisionNotFound;
import com.avisys.empmgmt.exception.EmployeeException;
import com.avisys.empmgmt.external.EmailService;
import com.avisys.empmgmt.repository.DivisonRepository;
import com.avisys.empmgmt.repository.EmployeeRepo;

import jakarta.validation.Valid;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepo employeeRepository;
	
	@Autowired
	private DivisonRepository divisionRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private EmailService mailService;

	public List<EmployeeDto> getAllEmployee(){
		List<Employee> employeeInfo=this.employeeRepository.findByIsDeletedFalse();
		
		List<EmployeeDto> employeeDtos = employeeInfo.stream()
				.map((Employee) -> this.modelMapper.map(Employee, EmployeeDto.class))
				.collect(Collectors.toList()).stream()
				.map((Employee) -> this.modelMapper.map(Employee, EmployeeDto.class))
				.collect(Collectors.toList());
			return employeeDtos;
	}

	@Override
	public EmployeeDto createEmployee(@Valid EmployeeDto employeeDto){
		Optional<Employee> optionalEmployee = employeeRepository.findByEmployeeCode(employeeDto.getEmployeeCode());
		if(employeeDto.getDivision()==null) {
			throw new EmployeeException("Division is not present");
		}
		Division division= divisionRepository.findByIdAndIsDeletedFalse(employeeDto.getDivision().getId()).orElseThrow(()-> new DivisionNotFound("Division Not Found"));
        if (optionalEmployee.isPresent()) {
            throw new EmployeeException("Employee code should not be duplicate");
        }
		Employee employee = this.modelMapper.map(employeeDto, Employee.class);
		employee.setCreatedAt(LocalDateTime.now());
		employee.setCreatedBy(employeeDto.getCreatedBy());
		employee.setDeleted(false);
		employee.setUpdatedBy(null);
		Employee employeeObject=employeeRepository.save(employee);
		
		MailData mailData=new MailData();	
		mailData.setId(44L);
		mailData.setEmail(employeeDto.getEmail());
		
		Map<String, String> map=new HashMap<>();
		map.put("empName", employeeDto.getFirstName());
		map.put("companyName", "Avisys Services Private Limited");
		map.put("team", "Avisys HRMS Team" );
		mailData.setData(map);
		mailService.sendMail(mailData);
		return this.modelMapper.map(employeeObject, EmployeeDto.class);
	}

	@Override
	public EmployeeDto getByEmployee(Long employeeId){
		Employee employee = employeeRepository.findByIdAndIsDeletedFalse(employeeId).orElseThrow(()-> new EmployeeException("Employee not found"));
			return this.modelMapper.map(employee, EmployeeDto.class);	
	}

	@Override
	public String deleteEmployee(Long employeeId,String updatedBy) {
		Employee employee = this.employeeRepository.findByIdAndIsDeletedFalse(employeeId).orElseThrow(()-> new EmployeeException("Employee not found"));
			employee.setDeleted(true); // Set isDeleted flag to true
			employee.setUpdatedAt(LocalDateTime.now());
			employee.setUpdatedBy(updatedBy);
			employeeRepository.save(employee); // Update the department entity
			return "Employee deleted successfully";
	}
	
	@Override
	public Page<EmployeeDto> searchEmployee(Pageable pageable, String keyword){
			keyword = keyword.toLowerCase();
		Page<Employee> Employee = employeeRepository.searchByEmployee(pageable,keyword);
		Page<EmployeeDto> employeeDto = (Page<EmployeeDto>) Employee
				.map((employee) -> this.modelMapper.map(employee, EmployeeDto.class));
		
			return employeeDto;
	}
	
	@Override
	public EmployeeDto updateEmployee(@Valid EmployeeDto employeeDto){
	
		if(employeeDto.getDivision()==null) {
			throw new EmployeeException("Division is not present");
		}
		Division division= divisionRepository.findByIdAndIsDeletedFalse(employeeDto.getDivision().getId()).orElseThrow(()-> new DivisionNotFound("Division Not Found"));

		Optional<Employee> optionalEmployee = employeeRepository.findByEmployeeCode(employeeDto.getEmployeeCode());
		if(!optionalEmployee.isPresent() || (optionalEmployee.isPresent() && employeeDto.getId() == optionalEmployee.get().getId())){
			
	    Employee employeeObj = employeeRepository.findByIdAndIsDeletedFalse(employeeDto.getId()).orElseThrow(()-> new EmployeeException("No Employee Present for given ID"));

	    employeeDto.setCreatedBy(employeeObj.getCreatedBy());
	    employeeDto.setCreatedAt(employeeObj.getCreatedAt());
	       
	    modelMapper.map(employeeDto, employeeObj);

	        employeeObj.setUpdatedAt(LocalDateTime.now());
	        employeeObj.setUpdatedBy(employeeDto.getUpdatedBy());
	        employeeObj.setDeleted(false);

	        Employee employee = employeeRepository.save(employeeObj);
	
	    return this.modelMapper.map(employee, EmployeeDto.class);
	}
		else throw new EmployeeException("employeeCode must not be duplicate"); 
}
	
}