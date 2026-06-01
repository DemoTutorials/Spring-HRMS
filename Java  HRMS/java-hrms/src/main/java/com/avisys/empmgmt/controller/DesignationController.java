package com.avisys.empmgmt.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import com.avisys.empmgmt.service.IDesignationService;
import com.avisys.empmgmt.util.ApiResponse;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("designation")
public class DesignationController {
	
	private Logger logger = Logger.getLogger(DesignationController.class);
	
	@Autowired
	private IDesignationService designationService;

	@GetMapping("/get-all")
	public ResponseEntity<?> getAllDesignation(){
		logger.info("/get-all-designation called");
		List<DesignationDto> allDesignations= designationService.getAllDesignation();
		return ResponseEntity.ok(allDesignations);
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createDesignation(@Valid @RequestBody CreateDesignationDto designation){
		logger.info("/save-designation called");
		return ResponseEntity.ok(designationService.createDesignation(designation));
	}
	
	@GetMapping("/{designationId}")
	public ResponseEntity<?> getDesignationById(@PathVariable String designationId){
		logger.info("/get-designation-by-id called");
		return ResponseEntity.ok(designationService.getDesignationById(designationId.toUpperCase()));
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateDesignation(@Valid @RequestBody DesignationDto designation){
		logger.info("/update-designation called");
		return ResponseEntity.ok(designationService.updateDesignation(designation));
	}
	
	@DeleteMapping("/{designationId}")
	public ResponseEntity<?> deleteDesignationById(@PathVariable String designationId,@RequestParam(value = "updatedBy") String updatedBy){
		logger.info("/delete-designation-by-id called");
		return ResponseEntity.ok(new ApiResponse(designationService.deleteDesignationById(designationId.toUpperCase(),updatedBy),LocalDateTime.now()));
	
	}

	@GetMapping("/search")
	public ResponseEntity<?> searchDesignationp(@RequestParam(defaultValue = "") String keyword, @PageableDefault Pageable pageable){
		logger.info("/search-designation called");
		return ResponseEntity.ok(designationService.searchDesignation(keyword,pageable));
	}
}
