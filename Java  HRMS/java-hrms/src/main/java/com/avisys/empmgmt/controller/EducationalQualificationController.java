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

import com.avisys.empmgmt.dto.AddressDto;
import com.avisys.empmgmt.dto.EducationalQualificationDto;
import com.avisys.empmgmt.service.EducationalQualificationService;
import com.avisys.empmgmt.util.ApiResponse;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("education")
public class EducationalQualificationController {

	@Autowired
	private EducationalQualificationService educationalQualificationService;
	
	@PostMapping("/{employee-id}/add")
	public ResponseEntity<EducationalQualificationDto> addEducationalQualification(@Valid @RequestBody EducationalQualificationDto educationalQualificationDto, @PathVariable("employee-id") Long employeeId) {
		EducationalQualificationDto createEducationalQualification = educationalQualificationService.addEducationalQualification(educationalQualificationDto,employeeId);
		return new ResponseEntity<EducationalQualificationDto>(createEducationalQualification, HttpStatus.OK);
	}
	
	@GetMapping("/{employee-id}/get-all")
	public ResponseEntity<List<EducationalQualificationDto>> getEducationalQualificationByEmployeeId(@PathVariable("employee-id") Long employeeId){
		List<EducationalQualificationDto> getEducationalQualification = this.educationalQualificationService.getEducationalQualificationByEmployee(employeeId);
		return new ResponseEntity<List<EducationalQualificationDto>>(getEducationalQualification,HttpStatus.OK);
	}
	
	@GetMapping("/{employee-id}/{education-id}")
	public ResponseEntity<EducationalQualificationDto> getEducationById(@PathVariable("employee-id") Long employeeId, @PathVariable("education-id") Long educationId){
		EducationalQualificationDto getEducation = this.educationalQualificationService.getByEmployeeIdAndEducationId(employeeId, educationId);
		return new ResponseEntity<EducationalQualificationDto>(getEducation,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{employee-id}/{education-id}")
	public ResponseEntity<?> deleteEducationalQualification(@PathVariable("employee-id") Long employeeId, @PathVariable("education-id") Long educationId,@RequestParam(value = "updatedBy") String updatedBy ){
		String deletedEducationalQualification = this.educationalQualificationService.deleteEducationalQualification(employeeId,educationId,updatedBy);
		return new ResponseEntity<>(new ApiResponse(deletedEducationalQualification,LocalDateTime.now()), HttpStatus.OK);
	}
	
	@PutMapping("/{employee-id}/update")
	public ResponseEntity<EducationalQualificationDto> updateEducationalQualification(@Valid @RequestBody EducationalQualificationDto educationalQualificationDto, @PathVariable("employee-id") Long employeeId ) {
		EducationalQualificationDto updateEducationalQualification = this.educationalQualificationService.updateEducationalQualification(educationalQualificationDto,employeeId);
		return new ResponseEntity<EducationalQualificationDto>(updateEducationalQualification, HttpStatus.OK);
	}
	
	@GetMapping("/{employee-id}/search")
	public ResponseEntity<Page<EducationalQualificationDto>> searchEducationalQualification(
	        @PageableDefault Pageable pageable,
	        @PathVariable("employee-id") Long employeeId,
	        @RequestParam(value = "keyword", defaultValue = "") String keyword
	) {
	    Page<EducationalQualificationDto> result = this.educationalQualificationService.searchEducationalQualification(pageable, keyword, employeeId);
	    return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
}
