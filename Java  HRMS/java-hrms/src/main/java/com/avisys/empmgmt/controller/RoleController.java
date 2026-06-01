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

import com.avisys.empmgmt.dto.CreateDTO;
import com.avisys.empmgmt.dto.GETResponse;
import com.avisys.empmgmt.dto.UpdateDTO;
import com.avisys.empmgmt.entity.Role;
import com.avisys.empmgmt.service.RoleService;
import com.avisys.empmgmt.util.ApiResponse;
import com.avisys.empmgmt.util.Utils;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private Utils util;

	Logger logger = Logger.getLogger(RoleController.class);

	@GetMapping("/all-roles")
	public ResponseEntity<?> getRoles() {

		logger.warn(RoleController.class.getName() + ":GET ROLES Method called");

		List<GETResponse> allRoles = roleService.getRoles();
		return new ResponseEntity(allRoles, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<?> createRole(@Valid @RequestBody CreateDTO role) {
		logger.warn(RoleController.class.getName() + ":POST CREATE ROLE Method called");

		// Spring-boot-validations are used
//		if(util.checkInvalidFields(role)>0) 
//			throw new InvalidInputException();

		GETResponse newlyCreatedRole = roleService.createRole(role);
		return new ResponseEntity(newlyCreatedRole, HttpStatus.OK);

	}

	@GetMapping("/search/{id}")
	public ResponseEntity<Role> findById(@PathVariable String id) {
		logger.warn(RoleController.class.getName() + ":GET SEARCH BY ID Method called");
		Role roleFounded = roleService.findById(id);
		if (roleFounded == null)
			return new ResponseEntity(new ApiResponse("No such role exist!",LocalDateTime.now()), HttpStatus.NOT_FOUND);
		return new ResponseEntity(roleFounded, HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<?> searchingSortingPagination(@RequestParam(defaultValue = "") String keyword,
			Pageable pageable) {
		logger.warn(RoleController.class.getName() + ":GET SEARCH+SORT+PAGINATION  Method called");
		Optional<Page<Role>> pages = this.roleService.searchingSortingPagination(keyword, pageable);
		return new ResponseEntity(pages, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateRole(@Valid @RequestBody UpdateDTO update) {
		logger.warn(RoleController.class.getName() + ":PUT UPDATE ROLE Method called");
		GETResponse updatedRole = this.roleService.updateRole(update);
		return new ResponseEntity(updatedRole, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteRole(@PathVariable("id") String id,@RequestParam(value = "updatedBy") String updatedBy) {
		logger.warn(RoleController.class.getName() + ":DELETE DELETE ROLE Method called");
		if (this.roleService.deleteRole(id,updatedBy) == false) {
			return new ResponseEntity(new ApiResponse("Permission denied!",LocalDateTime.now()), HttpStatus.BAD_REQUEST);
		} else {

			return new ResponseEntity(new ApiResponse("Role Deleted!",LocalDateTime.now()), HttpStatus.OK);

		}

	}

}
