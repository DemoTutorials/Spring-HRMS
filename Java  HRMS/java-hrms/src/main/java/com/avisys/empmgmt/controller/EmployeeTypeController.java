package com.avisys.empmgmt.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.avisys.empmgmt.dto.EmployeeTypeDTO;
import com.avisys.empmgmt.entity.EmployeeType;
import com.avisys.empmgmt.exception.ResourceNotFoundException;
import com.avisys.empmgmt.service.EmployeeTypeService;
import com.avisys.empmgmt.util.ApiResponse;

 

import jakarta.validation.Valid;

 

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("employee-type")
public class EmployeeTypeController {

 

    @Autowired
    EmployeeTypeService employeeTypeService;

 

    Logger logger = LoggerFactory.getLogger(EmployeeTypeController.class);

 

    /************** Save *******************/
    @PostMapping("/create")
    public ResponseEntity<EmployeeTypeDTO> addEmployeeType(@Valid @RequestBody EmployeeTypeDTO employeeTypeDTO) {
        EmployeeTypeDTO createEmployeeType = this.employeeTypeService.createEmployeeType(employeeTypeDTO);
        createEmployeeType.setMessage("Record Save Successfully...");
        return new ResponseEntity<EmployeeTypeDTO>(createEmployeeType, HttpStatus.OK);
    }

 

    /************** Show-All *******************/
    @GetMapping("/get-all")
    public ResponseEntity<?> lists() throws ResourceNotFoundException {

 

        logger.info("Getting All Information Successfully...");
        List<EmployeeTypeDTO> list = employeeTypeService.list();
        return ResponseEntity.ok(list);

 

    }

 

 

    /************** Get-ById *******************/
    @GetMapping("/by-id/{employeeTypeId}")
    public ResponseEntity<EmployeeTypeDTO> findEmpId(@PathVariable String employeeTypeId) {
        EmployeeTypeDTO employeeTypeDTO = employeeTypeService.getByEmpId(employeeTypeId);
        logger.info("Getting Information By Id Successfully...");
        employeeTypeDTO
                .setMessage("Record with ID:- " + employeeTypeDTO.getEmployeeTypeId() + " " + "Found Successfully...");
        return ResponseEntity.ok(employeeTypeDTO);
    }

 

    /************** Delete-ById *******************/
    @DeleteMapping("/delete/{employeeTypeId}")
    public ResponseEntity<?> delete(@PathVariable String employeeTypeId,@RequestParam(value = "updatedBy") String updatedBy) {
        ResponseEntity<?> employeeDeleted = employeeTypeService.delete(employeeTypeId,updatedBy);
        logger.info("Delete Information By Id Successfully...");
        return employeeDeleted;
    }

 

    /************** Update-ById *******************/
    @PutMapping("/update")
    public ResponseEntity<EmployeeTypeDTO> updateEmployee(@Valid @RequestBody EmployeeTypeDTO employeeTypeDTO) {
        logger.info("updateEmployeeType method called");
        EmployeeTypeDTO employeeType = employeeTypeService.updateEmployeeType(employeeTypeDTO);
        LocalDateTime dateTime = employeeType.getCreatedAt();
        employeeType.setMessage("Update Record with ID:- " + employeeTypeDTO.getEmployeeTypeId() + " Successfully...");
        employeeType.setCreatedAt(dateTime.now());
        return new ResponseEntity<EmployeeTypeDTO>(employeeType, HttpStatus.OK);
    }

 

    /************** Pagination *******************/

 

    @GetMapping("/search")
    public ResponseEntity<?> searchingSortingPagination(@RequestParam(defaultValue = "") String keyword,@PageableDefault(direction = Direction.ASC) Pageable pageable){
        logger.info("pagination method called");
        return new ResponseEntity<>(employeeTypeService.searchingSortingPagination(keyword.toLowerCase(),pageable),HttpStatus.OK);    
    }

 

}