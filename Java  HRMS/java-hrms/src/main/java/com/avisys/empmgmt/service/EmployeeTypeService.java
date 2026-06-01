package com.avisys.empmgmt.service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.avisys.empmgmt.dto.EmployeeTypeDTO;
import com.avisys.empmgmt.entity.EmployeeType;
import com.avisys.empmgmt.exception.ResourceNotFoundException;
import com.avisys.empmgmt.exception.EmployeeException;
import com.avisys.empmgmt.exception.EmployeeTypeException;
import com.avisys.empmgmt.exception.NoEmployeeFoundException;
import com.avisys.empmgmt.repository.EmployeeTypeRepository;
import com.avisys.empmgmt.util.ApiResponse;
import com.avisys.empmgmt.util.Utils;

 

import jakarta.validation.Valid;

 

@Service
public class EmployeeTypeService {
    @Autowired
    public EmployeeTypeRepository employeeTypeRepository;

 

    @Autowired
    private Utils util;
    @Autowired
    public ModelMapper mapper;

 

    /************* Show All ******************/

 

    public List<EmployeeTypeDTO> list() throws ResourceNotFoundException {

 

        List<EmployeeType> list = null;

 

        list = employeeTypeRepository.findAllByIsDeleted(false);
      
            List<EmployeeTypeDTO> empDTO = list.stream().map(e -> mapper.map(e, EmployeeTypeDTO.class))
                    .collect(Collectors.toList());
            return empDTO;
        }
 



 

    /************* Create Record ******************/

    public EmployeeTypeDTO createEmployeeType(@Valid EmployeeTypeDTO employeeTypeDTO) {
        Optional<EmployeeType> optionalEmployeeType = employeeTypeRepository
                .findByEmployeeTypeId(employeeTypeDTO.getEmployeeTypeId());
        if (optionalEmployeeType.isPresent()) {
            if (optionalEmployeeType.get().isDeleted()) {
                throw new EmployeeException("EmployeeType ID already present but marked deleted");
            } else {
                throw new EmployeeException("EmployeeType ID already present");
            }
        }

        EmployeeType employeeType = this.mapper.map(employeeTypeDTO, EmployeeType.class);
        employeeType.setCreatedAt(LocalDateTime.now());
        employeeType.setCreatedBy(employeeTypeDTO.getCreatedBy());
        employeeType.setUpdatedAt(LocalDateTime.now());
        employeeType.setUpdatedBy(null);
        employeeType.setDeleted(false);
        EmployeeType employeeTypeObject = employeeTypeRepository.save(employeeType);
        return this.mapper.map(employeeTypeObject, EmployeeTypeDTO.class);
    }

 

    /************* Get-ById ******************/

 

    public EmployeeTypeDTO getByEmpId(String employeeTypeId) throws ResourceNotFoundException {
        Optional<EmployeeType> findByIdAndDeleted = employeeTypeRepository
                .findByEmployeeTypeIdAndIsDeleted(employeeTypeId, false);
        if (findByIdAndDeleted.isEmpty()) {
            throw new ResourceNotFoundException("Record with Id " + employeeTypeId + " " + "does not exist");
        }
        EmployeeType employeeType = findByIdAndDeleted.get();
        EmployeeTypeDTO employeeTypeDTO = mapper.map(employeeType, EmployeeTypeDTO.class);
        return employeeTypeDTO;

 

    }

 

    /************* Delete-ById ******************/

 

    public ResponseEntity<?> delete(String employeeTypeId,String updatedBy) {
        Optional<EmployeeType> findByIdAndDeleted = employeeTypeRepository
                .findByEmployeeTypeIdAndIsDeleted(employeeTypeId, false);

 

        if (findByIdAndDeleted.isPresent()) {
            EmployeeType employee = findByIdAndDeleted.get();
            employee.setDeleted(true);
            employee.setUpdatedAt(LocalDateTime.now());
            employee.setUpdatedBy(updatedBy);
            employeeTypeRepository.save(employee);
            LocalDateTime dateTime = employee.getCreatedAt();
            return ResponseEntity.ok(new ApiResponse("Record Deleted Successfully",dateTime.now()));
        } else {
            throw new ResourceNotFoundException("Record with Id " + employeeTypeId + " Not Found...");
        }
    }

 

    /************* Update-ById ******************/

 

    public EmployeeTypeDTO updateEmployeeType(EmployeeTypeDTO employeeTypeDTO){
        if (employeeTypeDTO.getId() == null) {
            throw new EmployeeTypeException("Id Should Not be null");
        }
Optional<EmployeeType> employeeTypeOptionalObj = employeeTypeRepository.findById(employeeTypeDTO.getId());

        if (employeeTypeOptionalObj.isEmpty() || employeeTypeOptionalObj.get().isDeleted() == true) {
            String message = "No EmployeeType Present in the Database";
            throw new EmployeeTypeException(message);
        }

        EmployeeType employeeTypeObj = employeeTypeOptionalObj.get();
        Optional<EmployeeType> employeeTypeIdObj = employeeTypeRepository.findByEmployeeTypeId(employeeTypeDTO.getEmployeeTypeId());
        if (employeeTypeIdObj.isEmpty()|| employeeTypeDTO.getEmployeeTypeId().equals(employeeTypeObj.getEmployeeTypeId())) {
            EmployeeType employeeeType = employeeTypeOptionalObj.get();
            employeeeType.setEmployeeTypeId(employeeTypeDTO.getEmployeeTypeId());
            employeeeType.setType(employeeTypeDTO.getType());
            employeeeType.setOrgCode(employeeTypeDTO.getOrgCode());
            employeeeType.setUpdatedBy(employeeTypeDTO.getUpdatedBy());
            employeeeType.setUpdatedAt(LocalDateTime.now());
            employeeeType.setDeleted(false);
            EmployeeType empTypeObject= employeeTypeRepository.save(employeeeType);
            return  this.mapper.map(empTypeObject, EmployeeTypeDTO.class);
        } else {
            throw new EmployeeTypeException("Invalid EmployeeTypeId ");
        }
    }

    /************* Pagination-Search,Sort,Page,Size ******************/

 

    public Page<EmployeeType> searchingSortingPagination(String key, Pageable pageable) {
        Page<EmployeeType> employees = employeeTypeRepository.searchEmployeeType(key, pageable)
                .orElseThrow(() -> new EmployeeTypeException("EmployeeType Not Found"));

            return employees;        
    }
}