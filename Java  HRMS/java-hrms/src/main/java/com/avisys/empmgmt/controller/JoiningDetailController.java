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
import com.avisys.empmgmt.dto.CreateJoiningDetailDTO;
import com.avisys.empmgmt.dto.JoiningDetailDTO;
import com.avisys.empmgmt.service.JoiningDetailService;
import com.avisys.empmgmt.util.ApiResponse;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("joining-detail")
public class JoiningDetailController {
	
	@Autowired
	private JoiningDetailService joiningDetailService;

	public JoiningDetailController() {
		super();
	}
	
	@PostMapping("/create/{employee-id}")
    public ResponseEntity<JoiningDetailDTO> createJoiningDetail(@Valid @RequestBody CreateJoiningDetailDTO joiningDetailDto,@PathVariable("employee-id") Long employeeId){
		JoiningDetailDTO joiningDetails = joiningDetailService.createJoiningDetail(joiningDetailDto,employeeId);
    	return new ResponseEntity<JoiningDetailDTO>(joiningDetails,HttpStatus.OK);	
    }
	
	@DeleteMapping("/{employee-id}")
	public ResponseEntity<?> deleteJoiningDetailByEmployeeId(@PathVariable("employee-id") Long employeeId,@RequestParam(value = "updatedBy") String updatedBy){
		String message = joiningDetailService.deleteJoiningDetailByEmployeeId(employeeId,updatedBy);
		return new ResponseEntity<>(new ApiResponse(message, LocalDateTime.now()),HttpStatus.OK);
	}
	
	@PutMapping("/update/{employee-id}")
	public ResponseEntity<JoiningDetailDTO> updateJoiningDetail(@Valid @RequestBody JoiningDetailDTO joiningDetailDto,@PathVariable("employee-id") Long employeeId){
		JoiningDetailDTO JoiningDetails = joiningDetailService.updateJoiningDetail(joiningDetailDto,employeeId);
		return new ResponseEntity<JoiningDetailDTO>(JoiningDetails,HttpStatus.OK);
	}
	
	@GetMapping("/{employee-id}")
	public ResponseEntity<?> getJoiningDetailByEmployeeId(@PathVariable("employee-id") Long employeeId){
		return new ResponseEntity<>( joiningDetailService.getJoiningDetailByEmployeeId(employeeId),HttpStatus.OK);
	}

}
