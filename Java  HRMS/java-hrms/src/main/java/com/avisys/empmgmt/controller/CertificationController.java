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

import com.avisys.empmgmt.dto.CertificationDto;
import com.avisys.empmgmt.dto.WorkExperienceDto;
import com.avisys.empmgmt.service.CertificationService;
import com.avisys.empmgmt.service.WorkExperienceService;
import com.avisys.empmgmt.util.ApiResponse;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("certification")
public class CertificationController {
	
	@Autowired
	private CertificationService certificationService;
	
	@PostMapping("/{employee-id}/add")
	public ResponseEntity<CertificationDto> addCertification(@Valid @RequestBody CertificationDto certificationDto, @PathVariable("employee-id") Long employeeId) {
		CertificationDto certificate = certificationService.addCertification(certificationDto,employeeId);
		return new ResponseEntity<CertificationDto>(certificate, HttpStatus.OK);
	}
	
	@GetMapping("/{employee-id}/get-all")
	public ResponseEntity<List<CertificationDto>> getCertificationByEmployee(@PathVariable("employee-id") Long employeeId){
		List<CertificationDto> certificates = this.certificationService.getCertificationByEmployee(employeeId);
		return new ResponseEntity<List<CertificationDto>>(certificates,HttpStatus.OK);
	}
	
	 @GetMapping("/{employee-id}/{certificate-id}")
	 public ResponseEntity<CertificationDto> getCertificationById(@PathVariable("employee-id") Long employeeId, @PathVariable("certificate-id") Long certificationId){
	  CertificationDto getCertificate = this.certificationService.getByEmployeeIdAndCertificateId(employeeId, certificationId);
	  return new ResponseEntity<CertificationDto>(getCertificate,HttpStatus.OK);
   }
	  
	@DeleteMapping("/{employee-id}/{certification-id}")
	public ResponseEntity<?> deleteEmployeeWorkExperience(@PathVariable("employee-id") Long employeeId, @PathVariable("certification-id") Long certificationId,@RequestParam(value = "updatedBy") String updatedBy ){
		String deletedCertification = this.certificationService.deleteCertification(employeeId,certificationId,updatedBy);
		return new ResponseEntity<>(new ApiResponse(deletedCertification,LocalDateTime.now()), HttpStatus.OK);
	}
	
	@PutMapping("/{employee-id}/update")
	public ResponseEntity<CertificationDto> updateEmployeeWorkExperience(@Valid @RequestBody CertificationDto certificationDto, @PathVariable("employee-id") Long employeeId ) {
		CertificationDto updatedCertificationDto = this.certificationService.updateCertification(certificationDto,employeeId);
		return new ResponseEntity<CertificationDto>(updatedCertificationDto, HttpStatus.OK);
	}
	
	@GetMapping("/{employee-id}/search")
	public ResponseEntity<Page<CertificationDto>> searchCertification(
	        @PageableDefault Pageable pageable,
	        @PathVariable("employee-id") Long employeeId,
	        @RequestParam(value = "keyword", defaultValue = "") String keyword
	) {
	    Page<CertificationDto> result = this.certificationService.searchCertification(pageable, keyword, employeeId);
	    return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
