package com.avisys.empmgmt.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.avisys.empmgmt.dto.CreatePersonalDetailsDTO;
import com.avisys.empmgmt.dto.PersonalDetailsDTO;
import com.avisys.empmgmt.entity.Employee;
import com.avisys.empmgmt.entity.PersonalDetails;
import com.avisys.empmgmt.exception.DuplicatePersonalDetail;
import com.avisys.empmgmt.exception.EmployeeException;
import com.avisys.empmgmt.exception.NoPersonalDetailsFound;
import com.avisys.empmgmt.repository.EmployeeRepo;
import com.avisys.empmgmt.repository.PersonalDetailsRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class PersonalDetailsService {
    @Autowired
	private PersonalDetailsRepository personalDetailsRepo;
    
    @Autowired
    private ModelMapper modelMapper;
    
	@Autowired
	private EmployeeRepo employeeRepository;

	
	public PersonalDetailsDTO createPersonalDetails(CreatePersonalDetailsDTO personalDetails,Long employeeId) {	
		Employee employee=employeeRepository.findByIdAndIsDeletedFalse(employeeId).orElseThrow(()->new EmployeeException("Employee Not Found"));
		Optional<PersonalDetails> personalDetailsToValidate = personalDetailsRepo.findByAadhaarNumberOrPanCardNumberOrPassportNumber(personalDetails.getAadhaarNumber(),personalDetails.getPanCardNumber(),personalDetails.getPassportNumber());
		if(personalDetailsRepo.findByEmployee(employee).isPresent()) {
			throw new NoPersonalDetailsFound("Personal Details Already Exist");
		}
//		else if(personalDetailsToValidate.isPresent()&&!(personalDetailsToValidate.get().getEmployee().getId()==employeeId)) {
//			throw new DuplicatePersonalDetail("Personal Details Already Exist");
//		}
//		else {
		if(personalDetailsToValidate.isPresent()) {
			throw new DuplicatePersonalDetail("Personal Details already present");
		}
		else {
			PersonalDetails personalDetail = this.modelMapper.map(personalDetails,PersonalDetails.class);
			personalDetail.setCreatedAt(LocalDateTime.now());
			personalDetail.setUpdatedAt(null);
			personalDetail.setUpdatedBy(null);
			personalDetail.setDeleted(false);
			personalDetail.setEmployee(employee);
			PersonalDetails personalDetailObject=personalDetailsRepo.save(personalDetail);
			return this.modelMapper.map(personalDetailObject,PersonalDetailsDTO.class);
		}
//		}
	}

	public List<PersonalDetailsDTO> getAllPersonalDetails() {

		List<PersonalDetails> allPersonalDetails=personalDetailsRepo.findByIsDeletedFalse().orElseThrow(()->new NoPersonalDetailsFound("Personal Details Does Not Exist"));
		List<PersonalDetailsDTO> allPersonalDetailsDto=allPersonalDetails.stream()
				.map((PersonalDetail) -> this.modelMapper.map(PersonalDetail, PersonalDetailsDTO.class))
				.collect(Collectors.toList());

		return allPersonalDetailsDto;
		
	}

	public String deletePersonalDetails(Long employeeId,String updatedBy) {
		Employee employee=employeeRepository.findByIdAndIsDeletedFalse(employeeId).orElseThrow(()->new EmployeeException("Employee Not Found"));
		PersonalDetails personalDetailsToBeDeleted = personalDetailsRepo.findByIdAndIsDeletedFalse(employee.getPersonalDetails().getId()).orElseThrow(()->new NoPersonalDetailsFound("Personal Details Not found to delete for Employee Id "+employeeId));
		personalDetailsToBeDeleted.setDeleted(true);
		personalDetailsToBeDeleted.setUpdatedAt(LocalDateTime.now());
		personalDetailsToBeDeleted.setUpdatedBy(updatedBy);
		personalDetailsRepo.save(personalDetailsToBeDeleted);
		return "Personal Details Of Employee Deleted successfully";
	}

	public PersonalDetailsDTO updatePersonalDetails(@Valid PersonalDetailsDTO personalDetailsDto,Long employeeId) {
	    Employee employee=employeeRepository.findByIdAndIsDeletedFalse(employeeId).orElseThrow(()->new EmployeeException("Employee Not Found"));
	    if(personalDetailsDto.getId() == null) {
	    	throw new NoPersonalDetailsFound("Personal Details Id not found");
	    }
        PersonalDetails personalDetailToUpdate = personalDetailsRepo.findById(personalDetailsDto.getId()).orElseThrow(()->new NoPersonalDetailsFound("Personal Details not found to update"));
        Optional<PersonalDetails> personalDetailsToValidate = personalDetailsRepo.findByAadhaarNumberOrPanCardNumberOrPassportNumber(personalDetailsDto.getAadhaarNumber(),personalDetailsDto.getPanCardNumber(),personalDetailsDto.getPassportNumber());
        if (personalDetailToUpdate.isDeleted()) {
			throw new NoPersonalDetailsFound("Personal Details not found to update");
		}
		else if(personalDetailsToValidate.isPresent()&&!(personalDetailsToValidate.get().getEmployee().getId()==employeeId)) {
			throw new DuplicatePersonalDetail("Personal Details already present");
		}
        else {
        PersonalDetails updatedPersonalDetails = this.modelMapper.map(personalDetailsDto, PersonalDetails.class);
        updatedPersonalDetails.setCreatedAt(personalDetailToUpdate.getCreatedAt());
        updatedPersonalDetails.setUpdatedAt(LocalDateTime.now());
        updatedPersonalDetails.setDeleted(false);
        updatedPersonalDetails.setEmployee(employee);
        PersonalDetails updatedDetails = personalDetailsRepo.save(updatedPersonalDetails);
        
		return this.modelMapper.map(updatedDetails, PersonalDetailsDTO.class);
        }
	}

	public PersonalDetailsDTO getPersonalDetailsById(Long employeeId) {
		Employee employee=employeeRepository.findByIdAndIsDeletedFalse(employeeId).orElseThrow(()->new EmployeeException("Employee Not Found"));
	    PersonalDetails personalDetails = personalDetailsRepo.findByEmployeeAndIsDeletedFalse(employee).orElseThrow(()->new NoPersonalDetailsFound("Personal Detail Not found for Employee Id"+employeeId));
	    PersonalDetailsDTO personalDetailsDto = this.modelMapper.map(personalDetails,PersonalDetailsDTO.class);
		return personalDetailsDto;
	}

}
