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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avisys.empmgmt.dto.DepartmentDto;
import com.avisys.empmgmt.exception.DepartmentException;
import com.avisys.empmgmt.service.DepartmentService;
import com.avisys.empmgmt.util.ApiResponse;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("departments")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;

	@GetMapping("/allDepartments")
	public ResponseEntity<List<DepartmentDto>> getDepartments(){
		List<DepartmentDto> allDepartments = this.departmentService.getAllDepartments();
		return new ResponseEntity<List<DepartmentDto>>(allDepartments, HttpStatus.OK);
	}

	@GetMapping("/{department-Id}")
	public ResponseEntity<DepartmentDto> getByIdDepartments(@PathVariable("department-Id") String departmentId) {
		DepartmentDto getDesignations = this.departmentService.getByIdDepartments(departmentId);
		return new ResponseEntity<DepartmentDto>(getDesignations, HttpStatus.OK);
	}

	@PostMapping("/add-department")
	public ResponseEntity<DepartmentDto> addDepartment(@Valid @RequestBody DepartmentDto department){
		DepartmentDto createDeDepartmentDtopartment = this.departmentService.createDepartment(department);
		return new ResponseEntity<DepartmentDto>(createDeDepartmentDtopartment, HttpStatus.OK);
	}

	@DeleteMapping("/{department-Id}")
	public ResponseEntity<?> deleteDepartment(@PathVariable("department-Id") String departmentId,@RequestParam(value = "updatedBy") String updatedBy)
			throws DepartmentException {
		String deletedDepartment = this.departmentService.deleteDepartment(departmentId,updatedBy);
		return new ResponseEntity<>(new ApiResponse(deletedDepartment,LocalDateTime.now()), HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<DepartmentDto> updateDepartment(@Valid @RequestBody DepartmentDto department) {
		DepartmentDto updateDepartment = this.departmentService.updateDepartment(department);
		return new ResponseEntity<DepartmentDto>(updateDepartment, HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<Page<DepartmentDto>> searchDepartment(@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword, @PageableDefault Pageable pageable) {
		Page<DepartmentDto> result = this.departmentService.searchDepartment(pageable, keyword);
		return new ResponseEntity<Page<DepartmentDto>>(result, HttpStatus.OK);
	}
}