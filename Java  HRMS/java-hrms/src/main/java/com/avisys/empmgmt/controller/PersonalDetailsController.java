package com.avisys.empmgmt.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.avisys.empmgmt.dto.CreatePersonalDetailsDTO;
import com.avisys.empmgmt.dto.PersonalDetailsDTO;
import com.avisys.empmgmt.service.PersonalDetailsService;
import com.avisys.empmgmt.util.ApiResponse;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("personal-details")
public class PersonalDetailsController {

	@Autowired
	private PersonalDetailsService personalDetailsService;	

	public PersonalDetailsController() {
		super();
	}

	@PostMapping("/create/{employee-id}")
	public ResponseEntity<PersonalDetailsDTO> createPersonalDetails(@Valid @RequestBody CreatePersonalDetailsDTO personalDetailsDto,@PathVariable("employee-id") Long employeeId) {
		PersonalDetailsDTO personalDetails = personalDetailsService.createPersonalDetails(personalDetailsDto,employeeId);
		return new ResponseEntity<PersonalDetailsDTO>(personalDetails,HttpStatus.OK);

	}
	
	@GetMapping("/getAllPersonalDetails")
	public ResponseEntity<?> getAllPersonalDetails(){
		return new ResponseEntity<>(personalDetailsService.getAllPersonalDetails(),HttpStatus.OK);
	}
	
	@DeleteMapping("/{employee-id}")
	public ResponseEntity<?> deletePersonalDetail(@PathVariable("employee-id") Long employeeId,@RequestParam(value = "updatedBy") String updatedBy){
		String message = personalDetailsService.deletePersonalDetails(employeeId,updatedBy);
		return new ResponseEntity<>(new ApiResponse(message, LocalDateTime.now()),HttpStatus.OK);
	}
    
	@PutMapping("/update/{employee-id}")
	public ResponseEntity<PersonalDetailsDTO> updatePersonalDetails(@Valid @RequestBody PersonalDetailsDTO personalDetailsDto,@PathVariable("employee-id") Long employeeId){
		PersonalDetailsDTO personalDetails = personalDetailsService.updatePersonalDetails(personalDetailsDto,employeeId);
		return new ResponseEntity<PersonalDetailsDTO>(personalDetails,HttpStatus.OK);
	}
	
	@GetMapping("/{employee-id}")
	public ResponseEntity<?> getPersonalDetailsById(@PathVariable("employee-id") Long employeeId){
		PersonalDetailsDTO personalDetailsDto = personalDetailsService.getPersonalDetailsById(employeeId);
		return new ResponseEntity<>(personalDetailsDto,HttpStatus.OK);	
	}
	
}
