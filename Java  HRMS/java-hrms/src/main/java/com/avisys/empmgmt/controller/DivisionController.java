package com.avisys.empmgmt.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
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

import com.avisys.empmgmt.dto.CreateDivisionDto;
import com.avisys.empmgmt.dto.DivisionDto;
import com.avisys.empmgmt.service.IDivisionService;
import com.avisys.empmgmt.util.ApiResponse;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("division")

public class DivisionController {

	@Autowired
	private IDivisionService divisionService;

	Logger logger = Logger.getLogger(DivisionController.class);
	
	public DivisionController() {

	}

	@GetMapping("/get-all")
	public ResponseEntity<?> getAllDivison() {
        logger.info("getAllDivison method called");
		List<DivisionDto> divisons = divisionService.getAllDivision();
		return new ResponseEntity<>(divisons, HttpStatus.OK);
   }

	@PostMapping("/create")
	public ResponseEntity<?> createDivision(@Valid @RequestBody CreateDivisionDto division) {
		logger.info("createDivision method called");
		return new ResponseEntity<>(divisionService.saveDivision(division), HttpStatus.OK);
	}

	@DeleteMapping("/{divisionId}")
	public ResponseEntity<?> deleteDivisionById(@PathVariable("divisionId") String divisionId,@RequestParam(value = "updatedBy") String updatedBy) {
		logger.info("deleteDivisionById method called");
		return new ResponseEntity<>(new ApiResponse(divisionService.deleteDivisionById(divisionId,updatedBy),LocalDateTime.now()), HttpStatus.OK);
	}

	@GetMapping("/{divisionId}")
	public ResponseEntity<?> getDivisionById(@PathVariable("divisionId") String divisionId) {
		logger.info("getDivisionById method called");
		DivisionDto divisionDto = divisionService.getDivisionById(divisionId);
		return new ResponseEntity<>( divisionDto, HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> searchDivision(@RequestParam(defaultValue = "") String keyword,@PageableDefault(direction = Direction.ASC) Pageable pageable){
		logger.info("pagination method called");
		return new ResponseEntity<>(divisionService.searchDivision(keyword.toLowerCase(),pageable),HttpStatus.OK);	
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateDivision(@Valid @RequestBody DivisionDto divisionDto){
		logger.info("updateDivision method called");	
		return new ResponseEntity<>(divisionService.updateDivision(divisionDto),HttpStatus.OK);
	}

}
