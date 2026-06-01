package com.avisys.empmgmt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.avisys.empmgmt.dto.CreateDTO;
import com.avisys.empmgmt.dto.GETResponse;
import com.avisys.empmgmt.dto.OrganizationDTO;
import com.avisys.empmgmt.dto.UpdateDTO;
import com.avisys.empmgmt.entity.Organization;
import com.avisys.empmgmt.entity.Role;

public interface OrganizationService {

	public List<OrganizationDTO> getOrganizations(); 
	
	public OrganizationDTO createOrganization(OrganizationDTO role);
	
	public OrganizationDTO findById(String id);
	
	public String deleteOrganization(String id,String updatedBy);
	
	public OrganizationDTO updateOrganization(OrganizationDTO updateDto);
	
	public Optional<Page<Organization>> searchingSortingPagination(String key,Pageable  pageable);
}
