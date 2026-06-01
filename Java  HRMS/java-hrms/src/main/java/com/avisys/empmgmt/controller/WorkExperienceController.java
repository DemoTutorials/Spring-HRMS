package com.avisys.empmgmt.controller;


import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.avisys.empmgmt.dto.WorkExperienceDto;
import com.avisys.empmgmt.service.WorkExperienceService;
import com.avisys.empmgmt.util.ApiResponse;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("work-experience")
public class WorkExperienceController {
	
	@Autowired
	private WorkExperienceService workExperienceService;
	
	@PostMapping("/{employee-id}/add")
	public ResponseEntity<WorkExperienceDto> addWorkExperience(@Valid @RequestBody WorkExperienceDto workExperienceDto, @PathVariable("employee-id") Long employeeId) {
		WorkExperienceDto experience = workExperienceService.addWorkExperience(workExperienceDto,employeeId);
		return new ResponseEntity<WorkExperienceDto>(experience, HttpStatus.OK);
	}
	
	@GetMapping("/{employee-id}/get-all")
	public ResponseEntity<List<WorkExperienceDto>> getWorkExperienceByEmployeeId(@PathVariable("employee-id") Long employeeId){
		List<WorkExperienceDto> getExperiences = this.workExperienceService.getWorkExperienceByEmployee(employeeId);
		return new ResponseEntity<List<WorkExperienceDto>>(getExperiences,HttpStatus.OK);
	}
	
	@GetMapping("/{employee-id}/{work-experience-id}")
    public ResponseEntity<WorkExperienceDto> getEducationById(@PathVariable("employee-id") Long employeeId, @PathVariable("work-experience-id") Long workExperienceId){
		WorkExperienceDto getExperience = this.workExperienceService.getByEmployeeIdAndWorkExperienceId(employeeId, workExperienceId);
        return new ResponseEntity<WorkExperienceDto>(getExperience,HttpStatus.OK);
    }
	
	@DeleteMapping("/{employee-id}/{work-experience-id}")
	public ResponseEntity<?> deleteEmployeeWorkExperience(@PathVariable("employee-id") Long employeeId, @PathVariable("work-experience-id") Long workExperienceId,@RequestParam(value = "updatedBy") String updatedBy ){
		String deletedWorkExperience = this.workExperienceService.deleteWorkExperience(employeeId,workExperienceId,updatedBy);
		return new ResponseEntity<>(new ApiResponse(deletedWorkExperience,LocalDateTime.now()), HttpStatus.OK);
	}
	
	@PutMapping("/{employee-id}/update")
	public ResponseEntity<WorkExperienceDto> updateEmployeeWorkExperience(@Valid @RequestBody WorkExperienceDto workExperienceDto, @PathVariable("employee-id") Long employeeId ) {
		WorkExperienceDto updateWorkExperienceDto = this.workExperienceService.updateWorkExperience(workExperienceDto,employeeId);
		return new ResponseEntity<WorkExperienceDto>(updateWorkExperienceDto, HttpStatus.OK);
	}
	
	@GetMapping("/{employee-id}/search")
	public ResponseEntity<Page<WorkExperienceDto>> searchWorkExperience(
	        @PageableDefault Pageable pageable,
	        @PathVariable("employee-id") Long employeeId,
	        @RequestParam(value = "keyword", defaultValue = "") String keyword
	) {
	    Page<WorkExperienceDto> result = this.workExperienceService.searchWorkExperience(pageable, keyword, employeeId);
	    return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
