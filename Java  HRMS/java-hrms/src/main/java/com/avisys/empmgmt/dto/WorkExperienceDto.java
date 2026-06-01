package com.avisys.empmgmt.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class WorkExperienceDto {
	
	private Long id;
	
	@NotNull(message="companyName must not be null")
	@NotBlank(message="companyName must not be blank")
	private String companyName;
	
	@NotNull(message="Designation must not be null")
	@NotBlank(message="Designation must not be blank")
	private String designation;
	
	@NotNull(message="StartDate must not be null")
	private LocalDate fromDate;
	
	@NotNull(message="EndDate must not be null")
	private LocalDate toDate;
	
	@NotNull(message="address must not be null")
	@NotBlank(message="address must not be blank")
	private String address;
	
	@NotBlank(message = "Organisation Code should not be blank")
	@NotNull(message = "Organisation Code should not be null")
    @Pattern(regexp = "^[A-Za-z\\d][A-Za-z\\d-_]*[A-Za-z\\d]$",message = "Organization code should not contain any special characters except hypen,underscore but should not at starting and ending position")
	private String orgCode;
	
	private String createdBy;

	private String updatedBy;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public WorkExperienceDto() {
		super();
	}

	public WorkExperienceDto( String companyName, String designation, LocalDate fromDate, LocalDate toDate, String address, String orgCode, String createdBy, String updatedBy, LocalDateTime createdAt,LocalDateTime updatedAt) {
		super();
		this.companyName = companyName;
		this.designation = designation;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.address = address;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
