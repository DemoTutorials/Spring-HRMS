package com.avisys.empmgmt.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
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

import com.avisys.empmgmt.dto.AddressDto;
import com.avisys.empmgmt.service.AddressService;
import com.avisys.empmgmt.util.ApiResponse;

//import com.avisys.empmgmt.util.ApiResponse;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("address")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@PostMapping("/{employee-id}/create")
	public ResponseEntity<AddressDto> addAddress(@Valid @RequestBody AddressDto address, @PathVariable("employee-id") Long employeeId) {
		AddressDto createAddress = addressService.createAddress(address,employeeId);
		return new ResponseEntity<>(createAddress, HttpStatus.OK);
	}
	
	@PostMapping("/{employee-id}/add")
	public ResponseEntity<List<AddressDto>> addAddresses(@Valid @RequestBody List<AddressDto> addresses, @PathVariable("employee-id") Long employeeId) {
	    List<AddressDto> createdAddresses = new ArrayList<>();
	    
	    for (AddressDto address : addresses) {
	        AddressDto createdAddress = addressService.createAddress(address, employeeId);
	        createdAddresses.add(createdAddress);
	    }
	    
	    return new ResponseEntity<>(createdAddresses, HttpStatus.OK);
	}
	
	@GetMapping("/{employee-id}/get-all")
	  public ResponseEntity<List<AddressDto>> getAddressByEmployeeId(@PathVariable("employee-id") Long employeeId){
	    List<AddressDto> getAddress = this.addressService.getAddressByEmployee(employeeId);
	    return new ResponseEntity<List<AddressDto>>(getAddress,HttpStatus.OK);

	  }
	
	
	
	
	
//	@GetMapping("/{employee-id}/get-address")
//	public ResponseEntity<List<AddressDto>> getAddressByEmployeeId(@PathVariable("employee-id") Long employeeId){
//		AddressDto getAddress = this.addressService.getAddressByEmployee(employeeId);
//		return new ResponseEntity<List<AddressDto>>(getAddress,HttpStatus.OK);
//	}
	
	@GetMapping("/{employee-id}/{address-type}")
	public ResponseEntity<AddressDto> getAddressByEmployeeIdAndType(@PathVariable("employee-id") Long employeeId, @PathVariable("address-type") String addressType){
		AddressDto getAddress = this.addressService.getByEmployeeIdAndAddressType(employeeId, addressType);
		return new ResponseEntity<AddressDto>(getAddress,HttpStatus.OK);
	}
	
	@DeleteMapping("/{employee-id}/{address-id}")
	public ResponseEntity<?> deleteEmployeeAddress(@PathVariable("employee-id") Long employeeId, @PathVariable("address-id") Long addressId,@RequestParam(value = "updatedBy") String updatedBy ){
		String deletedAddress = this.addressService.deleteAddress(employeeId,addressId,updatedBy);
		return new ResponseEntity<>(new ApiResponse(deletedAddress,LocalDateTime.now()), HttpStatus.OK);
	}
	
	@PutMapping("/{employee-id}/update")
	public ResponseEntity<AddressDto> updateEmployeeAddress(@Valid @RequestBody AddressDto addressDto, @PathVariable("employee-id") Long employeeId ) {
		AddressDto updateEmployeeAddress = this.addressService.updateAddress(addressDto,employeeId);
		return new ResponseEntity<>(updateEmployeeAddress, HttpStatus.OK);
	}
	
	@PutMapping("/{employee-id}/update-addresses")
	public ResponseEntity<List<AddressDto>> updateEmployeeAddresses(@Valid @RequestBody List<AddressDto> addressDtos, @PathVariable("employee-id") Long employeeId) {
	    List<AddressDto> updatedAddresses = new ArrayList<>();

	    for (AddressDto addressDto : addressDtos) {
	        AddressDto updatedAddress = this.addressService.updateAddress(addressDto, employeeId);
	        updatedAddresses.add(updatedAddress);
	    }

	    return new ResponseEntity<>(updatedAddresses, HttpStatus.OK);
	}
}
