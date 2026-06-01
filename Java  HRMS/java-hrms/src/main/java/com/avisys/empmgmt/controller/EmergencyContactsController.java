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
import com.avisys.empmgmt.dto.EmergencyContactsDto;
import com.avisys.empmgmt.dto.VisaDto;
import com.avisys.empmgmt.service.EmergencyContactsService;
import com.avisys.empmgmt.util.ApiResponse;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("contact")
public class EmergencyContactsController {

	@Autowired
	private EmergencyContactsService emergencyContactsService;
	
	@PostMapping("/{employee-id}/add")
	public ResponseEntity<EmergencyContactsDto> addEmergencyContact(@Valid @RequestBody EmergencyContactsDto emergencyContactsDto, @PathVariable("employee-id") Long employeeId) {
		EmergencyContactsDto createEmergencyContact = emergencyContactsService.createEmergencyContact(emergencyContactsDto,employeeId);
		return new ResponseEntity<EmergencyContactsDto>(createEmergencyContact, HttpStatus.OK);
	}
	
	@GetMapping("/{employee-id}/get-all")
	public ResponseEntity<List<EmergencyContactsDto>> getEmergencyContactByEmployeeId(@PathVariable("employee-id") Long employeeId){
		List<EmergencyContactsDto> getEmergencyContacts = this.emergencyContactsService.getEmergencyContactsByEmployee(employeeId);
		return new ResponseEntity<List<EmergencyContactsDto>>(getEmergencyContacts,HttpStatus.OK);
	}
	
	  @GetMapping("/{employee-id}/{contact-id}")
	   public ResponseEntity<EmergencyContactsDto> getContactById(@PathVariable("employee-id") Long employeeId, @PathVariable("contact-id") Long contactId){
		  EmergencyContactsDto getContact = this.emergencyContactsService.getByEmployeeIdAndContactId(employeeId, contactId);
	       return new ResponseEntity<EmergencyContactsDto>(getContact,HttpStatus.OK);

	    }
	
	@DeleteMapping("/{employee-id}/{contact-id}")
	public ResponseEntity<?> deleteEmergencyContacts(@PathVariable("employee-id") Long employeeId, @PathVariable("contact-id") Long contactId,@RequestParam(value = "updatedBy") String updatedBy ){
		String deletedEmergencyContact = this.emergencyContactsService.deleteEmergencyContacts(employeeId,contactId,updatedBy);
		return new ResponseEntity<>(new ApiResponse(deletedEmergencyContact,LocalDateTime.now()), HttpStatus.OK);
	}
	
	@PutMapping("/{employee-id}/update")
	public ResponseEntity<EmergencyContactsDto> updateEmergencyContact(@Valid @RequestBody EmergencyContactsDto emergencyContactsDto, @PathVariable("employee-id") Long employeeId ) {
		EmergencyContactsDto updateEmergencyContacts = this.emergencyContactsService.updateEmergencyContacts(emergencyContactsDto,employeeId);
		return new ResponseEntity<EmergencyContactsDto>(updateEmergencyContacts, HttpStatus.OK);
	}
	
	@GetMapping("/{employee-id}/search")
	public ResponseEntity<Page<EmergencyContactsDto>> searchEmergencyContacts(
			@RequestParam(value = "keyword", defaultValue = "") String keyword,
	        @PageableDefault Pageable pageable,
	        @PathVariable("employee-id") Long employeeId
	) {
	    Page<EmergencyContactsDto> result = this.emergencyContactsService.searchEmergencyContacts(keyword, pageable, employeeId);
	    return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
