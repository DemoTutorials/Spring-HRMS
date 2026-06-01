package com.avisys.empmgmt.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.avisys.empmgmt.dto.DesignationDto;
import com.avisys.empmgmt.dto.CreateDesignationDto;


public interface IDesignationService {
	
	
	List<DesignationDto> getAllDesignation();

	DesignationDto createDesignation(CreateDesignationDto designation);
	
	DesignationDto getDesignationById(String designationId);

	DesignationDto updateDesignation(DesignationDto designation);

	String deleteDesignationById(String designationId,String updatedBy);
	
	Page<DesignationDto> searchDesignation(String designationKey,Pageable pageable);
}
