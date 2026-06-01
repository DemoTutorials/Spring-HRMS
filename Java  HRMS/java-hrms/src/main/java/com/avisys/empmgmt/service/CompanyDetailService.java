package com.avisys.empmgmt.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avisys.empmgmt.dto.CompanyDetailDTO;
import com.avisys.empmgmt.dto.CreateCompanyDetailDTO;
import com.avisys.empmgmt.entity.CompanyDetail;
import com.avisys.empmgmt.entity.Department;
import com.avisys.empmgmt.entity.Designation;
import com.avisys.empmgmt.entity.Employee;
import com.avisys.empmgmt.entity.EmployeeType;
import com.avisys.empmgmt.entity.Grade;
import com.avisys.empmgmt.entity.Role;
import com.avisys.empmgmt.exception.CompanyDetailAlreadyPresent;
import com.avisys.empmgmt.exception.CompanyDetailNotFound;
import com.avisys.empmgmt.exception.DepartmentException;
import com.avisys.empmgmt.exception.DesignationNotFound;
import com.avisys.empmgmt.exception.EmployeeException;
import com.avisys.empmgmt.exception.EmployeeTypeException;
import com.avisys.empmgmt.exception.GradeException;
import com.avisys.empmgmt.exception.NoRoleFoundException;
import com.avisys.empmgmt.repository.CompanyDetailRepository;
import com.avisys.empmgmt.repository.DepartmentRepo;
import com.avisys.empmgmt.repository.DesignationRepo;
import com.avisys.empmgmt.repository.EmployeeRepo;
import com.avisys.empmgmt.repository.EmployeeTypeRepository;
import com.avisys.empmgmt.repository.GradeRepository;
import com.avisys.empmgmt.repository.RoleRepo;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class CompanyDetailService {
	@Autowired
	private CompanyDetailRepository companyDetailRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private EmployeeRepo employeeRepository;
	
	@Autowired
	private DepartmentRepo departmentRepo;
	
	@Autowired
	private GradeRepository gradeRepository;
	
	@Autowired
	private DesignationRepo designationRepo;
	
	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private EmployeeTypeRepository employeeTypeRepository;

	public CompanyDetailDTO createCompanyDetail(CreateCompanyDetailDTO companyDetailDto,Long employeeId) {
		
		Employee employee=employeeRepository.findByIdAndIsDeletedFalse(employeeId).orElseThrow(()->new EmployeeException("Employee Not Found"));
		Department department=departmentRepo.findByIdAndIsDeletedFalse(companyDetailDto.getDepartment().getId()).orElseThrow(()->new DepartmentException("Department Not Founds"));
		Grade grade=gradeRepository.findByIdAndIsDeletedFalse(companyDetailDto.getGrade().getId()).orElseThrow(()->new GradeException("Grade Not Founds"));
		Designation designation=designationRepo.findByIdAndIsDeletedFalse(companyDetailDto.getDesignation().getId()).orElseThrow(()->new DesignationNotFound("Designation Not Founds"));
		Role role=roleRepo.findByIdAndIsDeletedFalse(companyDetailDto.getRole().getId()).orElseThrow(()->new NoRoleFoundException());
		EmployeeType employeeType=employeeTypeRepository.findByIdAndIsDeletedFalse(companyDetailDto.getEmployeeType().getId()).orElseThrow(()->new EmployeeTypeException("Employee Type Not Found"));
		if(companyDetailRepository.findByEmployee(employee).isPresent()) {
			throw new CompanyDetailAlreadyPresent("Company detail already present");
		}else {
		CompanyDetail companyDetail = this.modelMapper.map(companyDetailDto, CompanyDetail.class);
		companyDetail.setCreatedAt(LocalDateTime.now());
		companyDetail.setUpdatedBy(null);
		companyDetail.setDeleted(false);
		companyDetail.setEmployee(employee);
		CompanyDetail companyDetailObject=companyDetailRepository.save(companyDetail);	
		return this.modelMapper.map(companyDetailObject, CompanyDetailDTO.class);
	   }
	}

	public String deleteCompanyDetailByEmployeeId(Long employeeId,String updatedBy) {
		Employee employee=employeeRepository.findByIdAndIsDeletedFalse(employeeId).orElseThrow(()->new EmployeeException("Employee Not Found"));
		CompanyDetail companyDetailToDelete = companyDetailRepository
				.findByIdAndIsDeletedFalse(employee.getCompanyDetail().getId())
				.orElseThrow(() -> new CompanyDetailNotFound("Company Detail Not found to delete for Employee Id " + employeeId));
		companyDetailToDelete.setDeleted(true);
		companyDetailToDelete.setUpdatedAt(LocalDateTime.now());
		companyDetailToDelete.setUpdatedBy(updatedBy);
		companyDetailRepository.save(companyDetailToDelete);
		return "Company Detail Deleted";
	}

	public CompanyDetailDTO updateCompanyDetail(CompanyDetailDTO companyDetailDto,Long employeeId) {
		Employee employee=employeeRepository.findByIdAndIsDeletedFalse(employeeId).orElseThrow(()->new EmployeeException("Employee Not Found"));
		Department department=departmentRepo.findByIdAndIsDeletedFalse(companyDetailDto.getDepartment().getId()).orElseThrow(()->new DepartmentException("Department Not Founds"));
		Grade grade=gradeRepository.findByIdAndIsDeletedFalse(companyDetailDto.getGrade().getId()).orElseThrow(()->new GradeException("Grade Not Founds"));
		Designation designation=designationRepo.findByIdAndIsDeletedFalse(companyDetailDto.getDesignation().getId()).orElseThrow(()->new DesignationNotFound("Designation Not Founds"));
		Role role=roleRepo.findByIdAndIsDeletedFalse(companyDetailDto.getRole().getId()).orElseThrow(()->new NoRoleFoundException());
		EmployeeType employeeType=employeeTypeRepository.findByIdAndIsDeletedFalse(companyDetailDto.getEmployeeType().getId()).orElseThrow(()->new EmployeeTypeException("Employee Type Not Found"));
		CompanyDetail companyDetailToUpdate = companyDetailRepository.findById(companyDetailDto.getId())
				.orElseThrow(() -> new CompanyDetailNotFound("Company Detail Not found to update"));
		if (companyDetailToUpdate.isDeleted()) {
			throw new CompanyDetailNotFound("Company Details Not Found To Update");
		}
		else {
			companyDetailDto.setUpdatedAt(LocalDateTime.now());
			companyDetailDto.setCreatedBy(companyDetailToUpdate.getCreatedBy());
			companyDetailDto.setCreatedAt(companyDetailToUpdate.getCreatedAt());
			CompanyDetail companyDetail = this.modelMapper.map(companyDetailDto,CompanyDetail.class);
			companyDetail.setEmployee(employee);
			companyDetail.setDeleted(false);
			CompanyDetail companyDetailObj=companyDetailRepository.save(companyDetail);

		return this.modelMapper.map(companyDetailObj,CompanyDetailDTO.class);
		}
	}

	public CompanyDetailDTO getCompanyDetailById(Long employeeId) {
		Employee employee=employeeRepository.findByIdAndIsDeletedFalse(employeeId).orElseThrow(()->new EmployeeException("Employee Not Found"));
		CompanyDetail companyDetail = companyDetailRepository.findByEmployeeAndIsDeletedFalse(employee)
				.orElseThrow(() -> new CompanyDetailNotFound("Company Detail Not found for Employee Id "+employeeId));
		CompanyDetailDTO companyDetailDto = this.modelMapper.map(companyDetail, CompanyDetailDTO.class);
		return companyDetailDto;
	}
	
	

}
