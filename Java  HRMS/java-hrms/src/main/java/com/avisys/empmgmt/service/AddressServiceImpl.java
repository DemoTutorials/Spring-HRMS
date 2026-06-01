package com.avisys.empmgmt.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avisys.empmgmt.dto.AddressDto;
import com.avisys.empmgmt.entity.Address;
import com.avisys.empmgmt.entity.Employee;
import com.avisys.empmgmt.exception.AddressException;
import com.avisys.empmgmt.exception.EmployeeException;
import com.avisys.empmgmt.repository.AddressRepo;
import com.avisys.empmgmt.repository.EmployeeRepo;

import jakarta.validation.Valid;

@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	private AddressRepo addressRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private EmployeeRepo employeeRepository;
	


	@Override
	public AddressDto createAddress(@Valid AddressDto addressDto, Long employeeId){
		
		Employee employee=this.employeeRepository.findByIdAndIsDeletedFalse(employeeId).orElseThrow(()-> new EmployeeException("Employee not found"));
		
		List<Address> addressList = addressRepository.findByEmployee(employee);

		for (Address address : addressList) {
		    if (address.getAddressType().equals(addressDto.getAddressType())) {
		        throw new AddressException("Address Type already filled");
		    }
		}
		Address addressObject = this.modelMapper.map(addressDto, Address.class);
		addressObject.setCreatedAt(LocalDateTime.now());
		addressObject.setUpdatedBy(null);
		addressObject.setUpdatedAt(null);
		addressObject.setDeleted(false);
		addressObject.setEmployee(employee);
		Address address = addressRepository.save(addressObject);
		
		return this.modelMapper.map(address, AddressDto.class);
	}
	
	@Override
	public List<AddressDto> createAddresses(@Valid List<AddressDto> addressDtos, Long employeeId) {
	    Employee employee = this.employeeRepository.findByIdAndIsDeletedFalse(employeeId)
	            .orElseThrow(() -> new EmployeeException("Employee not found"));

	    List<AddressDto> createdAddresses = new ArrayList<>();

	    for (AddressDto addressDto : addressDtos) {
	        List<Address> addressList = addressRepository.findByEmployee(employee);

	        for (Address address : addressList) {
	            if (address.getAddressType().equals(addressDto.getAddressType())) {
	                throw new AddressException("Address Type already filled");
	            }
	        }

	        Address addressObject = this.modelMapper.map(addressDto, Address.class);
	        addressObject.setCreatedAt(LocalDateTime.now());
	        addressObject.setUpdatedBy(null);
	        addressObject.setUpdatedAt(null);
	        addressObject.setDeleted(false);
	        addressObject.setEmployee(employee);
	        Address address = addressRepository.save(addressObject);

	        createdAddresses.add(this.modelMapper.map(address, AddressDto.class));
	    }

	    return createdAddresses;
	}
	
	  @Override
	  public List<AddressDto> getAddressByEmployee(Long employeeId){
	    Employee employee = employeeRepository.findByIdAndIsDeletedFalse(employeeId).orElseThrow(()->new EmployeeException("Employee not found"));
	    List<Address> addresses=this.addressRepository.findByEmployeeAndIsDeletedFalse(employee);
	    if (addresses.isEmpty()) {
	        throw new AddressException("No addresses found for this employee");
	    }
	    List<AddressDto> addressDto= addresses.stream().map((address)-> this.modelMapper.map(address,AddressDto.class)).collect(Collectors.toList());
	    return addressDto;
	  }

	@Override
	public AddressDto updateAddress( AddressDto addressDto,Long employeeId) {
		Employee employee=employeeRepository.findByIdAndIsDeletedFalse(employeeId).orElseThrow(()->new EmployeeException("Employee not found"));

	    Address addressObj = addressRepository.findByIdAndIsDeletedFalse(addressDto.getId()).orElseThrow(()->new AddressException("Address not found"));
	    if(employee==addressObj.getEmployee()) {
	    	
	    	addressDto.setCreatedBy(addressObj.getCreatedBy());
	    	addressDto.setCreatedAt(addressObj.getCreatedAt());
	        modelMapper.map(addressDto, addressObj);
      
	        addressObj.setUpdatedAt(LocalDateTime.now());
	        addressObj.setUpdatedBy(addressDto.getUpdatedBy());
	        
	        addressObj.setDeleted(false);

	        Address address = addressRepository.save(addressObj);
	 
	    return  this.modelMapper.map(address, AddressDto.class);
	    } else throw new AddressException("EmployeeId doesn't have this addressId");
	}
	
	@Override
	public List<AddressDto> updateAddresses(@Valid List<AddressDto> addressDtos, Long employeeId) {
	    Employee employee = employeeRepository.findByIdAndIsDeletedFalse(employeeId)
	            .orElseThrow(() -> new EmployeeException("Employee not found"));

	    List<AddressDto> updatedAddresses = new ArrayList<>();

	    for (AddressDto addressDto : addressDtos) {
	        Address addressObj = addressRepository.findByIdAndIsDeletedFalse(addressDto.getId())
	                .orElseThrow(() -> new AddressException("Address not found"));

	        if (employee.equals(addressObj.getEmployee())) {
	            addressDto.setCreatedBy(addressObj.getCreatedBy());
	            addressDto.setCreatedAt(addressObj.getCreatedAt());
	            modelMapper.map(addressDto, addressObj);

	            addressObj.setUpdatedAt(LocalDateTime.now());
	            addressObj.setUpdatedBy(addressDto.getUpdatedBy());
	            addressObj.setDeleted(false);

	            Address address = addressRepository.save(addressObj);

	            updatedAddresses.add(this.modelMapper.map(address, AddressDto.class));
	        } else {
	            throw new AddressException("EmployeeId doesn't have this addressId");
	        }
	    }

	    return updatedAddresses;
	}

	@Override
	public String deleteAddress(Long employeeId, Long addressId,String updatedBy) {
		Employee employee=this.employeeRepository.findByIdAndIsDeletedFalse(employeeId).orElseThrow(()->new EmployeeException("Employee not found"));
		Address address = addressRepository.findByIdAndIsDeletedFalse(addressId).orElseThrow(()->new AddressException("Address not found"));
			
		if(employee==address.getEmployee()) {
			address.setDeleted(true);
			address.setUpdatedAt(LocalDateTime.now());
			address.setUpdatedBy(updatedBy);
			addressRepository.save(address); 
			return "Address deleted successfully";
		}else throw new AddressException("employeeId doesn't match with AddressId");
	}

	@Override
	public AddressDto getByEmployeeIdAndAddressType(Long employeeId, String addressType) {
		Employee employee = employeeRepository.findByIdAndIsDeletedFalse(employeeId).orElseThrow(()->new EmployeeException("Employee not found"));
		
		Address address=addressRepository.findByEmployeeAndAddressTypeAndIsDeletedFalse(employeeId,addressType);
		 if (address == null) {
		        throw new AddressException("Address not found");
		    }
		return this.modelMapper.map(address,AddressDto.class);
	}

	
}
