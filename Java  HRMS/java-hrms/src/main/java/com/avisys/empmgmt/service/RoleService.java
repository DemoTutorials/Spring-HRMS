package com.avisys.empmgmt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.avisys.empmgmt.dto.CreateDTO;
import com.avisys.empmgmt.dto.GETResponse;
import com.avisys.empmgmt.dto.UpdateDTO;
import com.avisys.empmgmt.entity.Role;

public interface RoleService {
	
	public List<GETResponse> getRoles(); 
	
	public GETResponse createRole(CreateDTO role);
	
	public Role findById(String id);
	
	public boolean deleteRole(String id,String updatedBy);
	
	public GETResponse updateRole(UpdateDTO updateDto);
	
	public Optional<Page<Role>> searchingSortingPagination(String key,Pageable  pageable);
	
}

