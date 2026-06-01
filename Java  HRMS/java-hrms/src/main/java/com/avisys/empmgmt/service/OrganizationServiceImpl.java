package com.avisys.empmgmt.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avisys.empmgmt.dto.DesignationDto;
import com.avisys.empmgmt.dto.GETResponse;
import com.avisys.empmgmt.dto.OrganizationDTO;
import com.avisys.empmgmt.entity.Designation;
import com.avisys.empmgmt.entity.Organization;
import com.avisys.empmgmt.entity.Role;
import com.avisys.empmgmt.exception.DesignationNotFound;
import com.avisys.empmgmt.exception.NoRoleFoundException;
import com.avisys.empmgmt.exception.OrganizationNotFound;
import com.avisys.empmgmt.exception.RoleAlreadyPresentException;
import com.avisys.empmgmt.repository.DesignationRepo;
import com.avisys.empmgmt.repository.OrganizationRepo;
import com.avisys.empmgmt.util.Utils;


@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {
	
	@Autowired
	private OrganizationRepo organizationRepo;
	
	@Autowired
	private Utils organizationUtils;

	@Override
	public List<OrganizationDTO> getOrganizations() {
		
		List<Organization> organizationList = organizationRepo.findByIsDeletedFalse()
				.orElseThrow(() -> new OrganizationNotFound("Organization List is empty"));
		List<OrganizationDTO> organizationDtoList = organizationList.stream()
				.map(o -> organizationUtils.organizationToOrganizationDto(o)).collect(Collectors.toList());
		return organizationDtoList;
		
	}

	@Override
	public OrganizationDTO createOrganization(OrganizationDTO organizationDto) {
			if(organizationRepo.findByOrganizationCode(organizationDto.getOrganizationCode()).isPresent()) {
			Organization deletedOrganization=this.organizationRepo.findByOrganizationCodeAndIsDeletedTrue(organizationDto.getOrganizationCode().toUpperCase());
			if(deletedOrganization!=null)
			{throw new OrganizationNotFound("Organization Code already present but marked deleted");}
			
			throw new OrganizationNotFound("Organization Code already present");
		}else {
			
			Organization organizationTocreate=new Organization(organizationDto.getOrganizationCode().toUpperCase(), organizationDto.getOrganizationName(), organizationDto.getOrganizationDesc(), false,LocalDateTime.now(), LocalDateTime.now(), organizationDto.getCreatedBy(),null);
			Organization organizationCreated= organizationRepo.save(organizationTocreate);	
		return organizationUtils.organizationToOrganizationDto(organizationCreated);
			}
	}

	@Override
	public OrganizationDTO findById(String orgCode) {
		Organization organization = organizationRepo.findByOrganizationCodeAndIsDeletedFalse(orgCode.toUpperCase())
				.orElseThrow(() -> new OrganizationNotFound("Organization  Not found"));
		return organizationUtils.organizationToOrganizationDto(organization);	}

	@Override
	public String deleteOrganization(String orgCode, String updatedBy) {
		Organization organizationToBeDeleted = organizationRepo
				.findByOrganizationCodeAndIsDeletedFalse(orgCode.toUpperCase())
				.orElseThrow(() -> new OrganizationNotFound("Organization Not found to delete with code " + orgCode));
		
		
		organizationToBeDeleted.setDeleted(true);
		organizationToBeDeleted.setUpdatedAt(LocalDateTime.now());
		organizationToBeDeleted.setUpdatedBy(updatedBy);
		organizationRepo.save(organizationToBeDeleted);
		return "Organization Deleted";
		
	}

	@Override
	public OrganizationDTO updateOrganization(OrganizationDTO updateDto) {
		Organization organizationToBeUpdated=this.organizationRepo.findById(updateDto.getId()).orElseThrow(()->new OrganizationNotFound("Organization not found"));
		Organization isOrganizationIdAlreadyPresent=organizationRepo.findByOrganizationCodeIgnoreCase(updateDto.getOrganizationCode());
		if(isOrganizationIdAlreadyPresent==null || (isOrganizationIdAlreadyPresent.getOrganizationCode().equals(updateDto.getOrganizationCode()) && isOrganizationIdAlreadyPresent.getId()==updateDto.getId()) )
		{
			organizationToBeUpdated.setOrganizationCode(updateDto.getOrganizationCode().toUpperCase());
			organizationToBeUpdated.setOrganizationName(updateDto.getOrganizationName());
			organizationToBeUpdated.setOrganizationDesc(updateDto.getOrganizationDesc());
			LocalDateTime now = LocalDateTime.now();
			organizationToBeUpdated.setUpdatedAt(now);
			organizationToBeUpdated.setUpdatedBy(updateDto.getUpdatedBy());
			Organization organizationUpdated= this.organizationRepo.save(organizationToBeUpdated); 
			return this.organizationUtils.organizationToOrganizationDto(organizationUpdated);
			
		}
		else
		throw new OrganizationNotFound("Organization already present ");
		
		
		}

	@Override
	public Optional<Page<Organization>> searchingSortingPagination(String key, Pageable pageable) {
		Optional<Page<Organization>>pages= this.organizationRepo.searchOrganization(key.toLowerCase(),pageable);
		return pages;
	}

}
