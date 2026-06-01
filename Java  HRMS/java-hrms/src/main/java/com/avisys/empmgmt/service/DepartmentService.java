package com.avisys.empmgmt.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.avisys.empmgmt.dto.DepartmentDto;

@Service
public interface DepartmentService {

	DepartmentDto createDepartment(DepartmentDto department);

	DepartmentDto updateDepartment(DepartmentDto departmentDto);

	List<DepartmentDto> getAllDepartments();


	String deleteDepartment(String Id,String updatedBy);

	Page<DepartmentDto> searchDepartment(Pageable pageable, String keyword);

	DepartmentDto getByIdDepartments(String departmentId);
}
