package com.avisys.empmgmt.dto;


import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class EducationalQualificationDto {
	
	private Long id;
	
	@NotNull(message="Qualification must not be null")
	@NotBlank(message="Qualification must not be blank")
	private String educationalQualification;

	@NotNull(message="Name must not be null")
	@NotBlank(message="Name must not be blank")
	private String instituteName;
	
	@NotNull(message="Name must not be null")
	@NotBlank(message="Name must not be blank")
	private String qualificationLevel;
	
	@NotNull(message="year must not be null")
	private LocalDate passingYear;
	
	@NotBlank(message = "Organisation Code should not be blank")
	@NotNull(message = "Organisation Code should not be null")
    @Pattern(regexp = "^[A-Za-z\\d][A-Za-z\\d-_]*[A-Za-z\\d]$",message = "Organization code should not contain any special characters except hypen,underscore but should not at starting and ending position")
	private String orgCode;
	
	private String createdBy;

	private String updatedBy;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public EducationalQualificationDto() {
		super();
	}

	public EducationalQualificationDto(String educationalQualification, String instituteName, String qualificationLevel, LocalDate passingYear, String orgCode, String createdBy, String updatedBy, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.educationalQualification = educationalQualification;
		this.instituteName = instituteName;
		this.qualificationLevel = qualificationLevel;
		this.passingYear = passingYear;
		this.orgCode = orgCode;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdAt=createdAt;
		this.updatedAt=updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEducationalQualification() {
		return educationalQualification;
	}

	public void setEducationalQualification(String educationalQualification) {
		this.educationalQualification = educationalQualification;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getQualificationLevel() {
		return qualificationLevel;
	}

	public void setQualificationLevel(String qualificationLevel) {
		this.qualificationLevel = qualificationLevel;
	}

	public LocalDate getPassingYear() {
		return passingYear;
	}

	public void setPassingYear(LocalDate passingYear) {
		this.passingYear = passingYear;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
		
}