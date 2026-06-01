package com.avisys.empmgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.avisys.empmgmt.dto.AddressDto;

import jakarta.validation.Valid;

@Service
public interface AddressService {

	AddressDto createAddress(@Valid AddressDto addressDto, Long employeeId);

	List<AddressDto> getAddressByEmployee(Long employeeId);

	AddressDto updateAddress(AddressDto addressDto, Long employeeId);

	String deleteAddress(Long employeeId, Long addressId,String updatedBy);

	AddressDto getByEmployeeIdAndAddressType(Long employeeId, String addressType);

	List<AddressDto> createAddresses(@Valid List<AddressDto> addressDtos, Long employeeId);

	List<AddressDto> updateAddresses(@Valid List<AddressDto> addressDtos, Long employeeId);

}