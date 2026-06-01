package com.avisys.empmgmt.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.avisys.empmgmt.dto.EmergencyContactsDto;
import com.avisys.empmgmt.dto.VisaDto;
import com.avisys.empmgmt.entity.EmergencyContacts;
import com.avisys.empmgmt.entity.Employee;
import com.avisys.empmgmt.entity.Visa;
import com.avisys.empmgmt.exception.AddressException;
import com.avisys.empmgmt.exception.EmergencyContactsException;
import com.avisys.empmgmt.exception.EmployeeException;
import com.avisys.empmgmt.repository.EmergencyContactsRepo;
import com.avisys.empmgmt.repository.EmployeeRepo;

import jakarta.validation.Valid;

@Service
public class EmergencyContactsServiceImpl implements EmergencyContactsService{

	@Autowired
	private EmergencyContactsRepo emergencyContactsRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private EmployeeRepo employeeRepository;
	
	@Override
	public EmergencyContactsDto createEmergencyContact(@Valid EmergencyContactsDto emergencyContactsDto, Long employeeId) {
		
		Employee employee=this.employeeRepository.findByIdAndIsDeletedFalse(employeeId).orElseThrow(()-> new EmployeeException("Employee not found"));
		
		Optional<EmergencyContacts> emergencyContactsObj = emergencyContactsRepository.findByEmergencyContactNumberAndIsDeletedFalse(emergencyContactsDto.getEmergencyContactNumber());
	    if (!emergencyContactsObj.isPresent()) {
		EmergencyContacts contacts = this.modelMapper.map(emergencyContactsDto, EmergencyContacts.class);
		contacts.setCreatedAt(LocalDateTime.now());
		contacts.setDeleted(false);
		contacts.setUpdatedBy(null);
		contacts.setEmployee(employee);
		EmergencyContacts contactsObject=emergencyContactsRepository.save(contacts);
		return this.modelMapper.map(contactsObject, EmergencyContactsDto.class);
	} else
   	 throw new EmergencyContactsException("Contact Number is already filled");
}

	@Override
	public List<EmergencyContactsDto> getEmergencyContactsByEmployee(Long employeeId) {
		Employee employee = employeeRepository.findByIdAndIsDeletedFalse(employeeId).orElseThrow(()->new EmployeeException("Employee not found"));
		
		
		List<EmergencyContacts> contacts=this.emergencyContactsRepository.findByEmployeeAndIsDeletedFalse(employee);
		List<EmergencyContactsDto> emergencyContactsDto = contacts.stream().map((contact)-> this.modelMapper.map(contact,EmergencyContactsDto.class)).collect(Collectors.toList());
		
		return emergencyContactsDto;
	}
	
	@Override
	public EmergencyContactsDto getByEmployeeIdAndContactId(Long employeeId, Long contactId) {
        Employee employee = employeeRepository.findByIdAndIsDeletedFalse(employeeId).orElseThrow(()->new EmployeeException("Employee not found"));
        EmergencyContacts contact=emergencyContactsRepository.findByIdAndIsDeletedFalse(contactId).orElseThrow(()->new EmergencyContactsException("Emergency Contact not found"));

        return this.modelMapper.map(contact,EmergencyContactsDto.class);

    }

	@Override
	public EmergencyContactsDto updateEmergencyContacts(EmergencyContactsDto emergencyContactsDto, Long employeeId) {
		Employee employee=employeeRepository.findByIdAndIsDeletedFalse(employeeId).orElseThrow(()->new EmployeeException("Employee not found"));

	    EmergencyContacts contactObj = emergencyContactsRepository.findByIdAndIsDeletedFalse(emergencyContactsDto.getId()).orElseThrow(()->new EmergencyContactsException("Contact not found"));
	    if(employee==contactObj.getEmployee()) {
	    	
	    	emergencyContactsDto.setCreatedBy(contactObj.getCreatedBy());
	    	emergencyContactsDto.setCreatedAt(contactObj.getCreatedAt());
	        modelMapper.map(emergencyContactsDto, contactObj);
	        
	        contactObj.setUpdatedAt(LocalDateTime.now());
	        contactObj.setUpdatedBy(emergencyContactsDto.getUpdatedBy());
	        contactObj.setDeleted(false);

	        EmergencyContacts contacts=emergencyContactsRepository.save(contactObj); 
	    return  this.modelMapper.map(contacts, EmergencyContactsDto.class);
	    
	    } else throw new EmergencyContactsException("EmployeeId doesn't have this contactId");
	}

	@Override
	public String deleteEmergencyContacts(Long employeeId, Long emergencyContactsId,String updatedBy) {
		Employee employee=this.employeeRepository.findByIdAndIsDeletedFalse(employeeId).orElseThrow(()->new EmployeeException("Employee not found"));
		EmergencyContacts contacts = this.emergencyContactsRepository.findByIdAndIsDeletedFalse(emergencyContactsId).orElseThrow(()->new EmergencyContactsException("Contact not found"));
			
		if(employee==contacts.getEmployee()) {
			contacts.setDeleted(true);
			contacts.setUpdatedAt(LocalDateTime.now());
			contacts.setUpdatedBy(updatedBy);
			emergencyContactsRepository.save(contacts); 
			return "contact deleted successfully";
		}else throw new EmergencyContactsException("EmployeeId doesn't match with EmergencyContactsId");
	}

	@Override
	public Page<EmergencyContactsDto> searchEmergencyContacts(String keyword, Pageable pageable, Long employeeId) {
		Employee employee = this.employeeRepository.findByIdAndIsDeletedFalse(employeeId)
                .orElseThrow(() -> new EmployeeException("Employee not found"));

        List<EmergencyContacts> emergencyContacts = emergencyContactsRepository.findByEmployeeAndIsDeletedFalse(employee);

        keyword = keyword.toLowerCase();
        Page<EmergencyContacts> pageableContacts = emergencyContactsRepository.searchByEmergencyContactsAndEmployeeId(keyword, pageable, employeeId);

        Page<EmergencyContactsDto> contactsDto = pageableContacts.map(contacts ->
                this.modelMapper.map(contacts, EmergencyContactsDto.class));

            return contactsDto;

	}
}
