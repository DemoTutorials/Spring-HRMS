package com.avisys.empmgmt.util;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.avisys.empmgmt.dto.DesignationDto;
import com.avisys.empmgmt.dto.DivisionDto;
import com.avisys.empmgmt.dto.EmployeeTypeDTO;
import com.avisys.empmgmt.dto.GETResponse;
import com.avisys.empmgmt.dto.OrganizationDTO;
import com.avisys.empmgmt.entity.Designation;
import com.avisys.empmgmt.entity.Division;
import com.avisys.empmgmt.entity.EmployeeType;
import com.avisys.empmgmt.entity.Organization;
import com.avisys.empmgmt.entity.Role;

@Component
public class Utils {

	// Helper Method to convert Role POJO into Role DTO
	public GETResponse roleToDTO(Role role) {
		return new GETResponse(role.getId(), role.getRoleName(), role.getRoleId(), role.getOrgCode(),
				role.getCreatedBy(), role.getCreatedAt(), role.getUpdatedBy(), role.getUpdatedAt());
	}

	public DesignationDto designationToDesignationDto(Designation designation) {
		return new DesignationDto(designation.getId(), designation.getDesignationId(), designation.getDesignationName(),
				designation.getDesignationDesc(), designation.getOrgCode(),
				designation.getCreatedAt(), designation.getUpdatedAt(), designation.getCreatedBy(),
				designation.getUpdatedBy());
	}

	public Designation designationDtoToDesignation(DesignationDto designationDto) {
		return new Designation(designationDto.getDesignationId(), designationDto.getDesignationName(),
				designationDto.getDesignationDesc(), designationDto.getOrgCode(), false,
				LocalDateTime.now(), LocalDateTime.now(), designationDto.getCreatedBy(), designationDto.getUpdatedBy());
	}

	public List<DivisionDto> getAllDivisionsDto(List<Division> divisions) {

		List<DivisionDto> divisionsDto = divisions.stream()
				.map((division) -> new DivisionDto(division.getId(), division.getDivisionId(),
						division.getdivisionName(), division.getdivisionDescription(), division.getOrgCode(),
						division.getCreatedAt(), division.getUpdatedAt(), division.getCreatedBy(),
						division.getUpdatedBy()))
				.collect(Collectors.toList());
		return divisionsDto;
	}

	public Division getDivision(DivisionDto updatedDivisionDto) {

		return new Division(updatedDivisionDto.getId(), updatedDivisionDto.getDivisionId(),
				updatedDivisionDto.getDivisionName(), updatedDivisionDto.getDivisionDescription(),
				updatedDivisionDto.getOrgCode(), false, updatedDivisionDto.getCreatedAt(),
				updatedDivisionDto.getUpdatedAt(), updatedDivisionDto.getCreatedBy(),
				updatedDivisionDto.getUpdatedBy());
	}

	public DivisionDto getDivisionDto(Division division) {
		return new DivisionDto(division.getId(), division.getDivisionId(), division.getdivisionName(),
				division.getdivisionDescription(), division.getOrgCode(), division.getCreatedAt(),
				division.getUpdatedAt(), division.getCreatedBy(), division.getUpdatedBy());
	}
    public EmployeeType getEmployeeType(EmployeeTypeDTO employeeTypeDTO) {
    	 

        return new EmployeeType(employeeTypeDTO.getId(), employeeTypeDTO.getType(), employeeTypeDTO.getOrgCode(), false,
                employeeTypeDTO.getCreatedAt(), employeeTypeDTO.getUpdatedAt(), employeeTypeDTO.getCreatedBy(),
                employeeTypeDTO.getUpdatedBy());
    }
    
    public OrganizationDTO organizationToOrganizationDto(Organization organization) {

        System.out.println("Hi converting"+organization.toString());

        return new OrganizationDTO(organization.getId(),organization.getOrganizationCode(),organization.getOrganizationName()

                ,organization.getOrganizationDesc(),

                organization.getCreatedAt(),organization.getUpdatedAt(),organization.getCreatedBy(),organization.getUpdatedBy());

    }
}
