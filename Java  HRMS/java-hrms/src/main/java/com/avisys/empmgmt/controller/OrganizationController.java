package com.avisys.empmgmt.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.avisys.empmgmt.dto.CreateDesignationDto;
import com.avisys.empmgmt.dto.DesignationDto;
import com.avisys.empmgmt.dto.GETResponse;
import com.avisys.empmgmt.dto.OrganizationDTO;
import com.avisys.empmgmt.dto.UpdateDTO;
import com.avisys.empmgmt.entity.Organization;
import com.avisys.empmgmt.entity.Role;
import com.avisys.empmgmt.service.IDesignationService;
import com.avisys.empmgmt.service.OrganizationService;
import com.avisys.empmgmt.util.ApiResponse;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("organization")
public class OrganizationController {
	
private Logger logger = Logger.getLogger(OrganizationController.class);
	
	@Autowired
	private OrganizationService organizationService;

	@GetMapping("/get-all")
	public ResponseEntity<?> getAllOrganizations(){
		logger.info("/get-all-organization called");
		List<OrganizationDTO> allOrganizations= organizationService.getOrganizations();
		return ResponseEntity.ok(allOrganizations);
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createOrganization(@Valid @RequestBody OrganizationDTO organization){
		logger.info("/create-organization called");
		return ResponseEntity.ok(organizationService.createOrganization(organization));
	}
	
	
	@GetMapping("/{organizationCode}")
	public ResponseEntity<?> getOrganizationByCode(@PathVariable String organizationCode){
		logger.info("/get-organization-by-code- called");
		return ResponseEntity.ok(organizationService.findById(organizationCode.toUpperCase()));
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateOrganization(@Valid @RequestBody OrganizationDTO organizationDto) {
		logger.info("/update-organization- called");
		OrganizationDTO updatedOrganization = this.organizationService.updateOrganization(organizationDto);
		return new ResponseEntity(updatedOrganization, HttpStatus.OK);
	}

	@DeleteMapping("/{organizationCode}")
	public ResponseEntity<?> deleteOrganizationByCode(@PathVariable String organizationCode,@RequestParam(value = "updatedBy") String updatedBy){
		logger.info("/delete-organization-by-id called");
		return ResponseEntity.ok(new ApiResponse(organizationService.deleteOrganization(organizationCode.toUpperCase(),updatedBy),LocalDateTime.now()));
	
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> searchingSortingPagination(@RequestParam(defaultValue = "") String keyword,
			Pageable pageable) {
		logger.info("/search pagination-called");
		Optional<Page<Organization>> pages = this.organizationService.searchingSortingPagination(keyword, pageable);
		return new ResponseEntity(pages, HttpStatus.OK);
	}
}
