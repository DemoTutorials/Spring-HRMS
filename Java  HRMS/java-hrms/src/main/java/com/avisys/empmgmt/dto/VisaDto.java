package com.avisys.empmgmt.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class VisaDto {

	private Long id;

	@NotNull(message="countryCode field must not be null")
	@NotBlank(message="countryCode field must not be blank")
	private String countryCode;

	@NotNull(message="visaNumber field must not be null")
	@NotBlank(message="visaNumber field must not be blank")
	private String visaNumber;

	@NotNull(message="validDate field must not be null")
	private LocalDate validDate;
	
	private String visaFile;
	
	@NotBlank(message = "Organisation Code should not be blank")
	@NotNull(message = "Organisation Code should not be null")
    @Pattern(regexp = "^[A-Za-z\\d][A-Za-z\\d-_]*[A-Za-z\\d]$",message = "Organization code should not contain any special characters except hypen,underscore but should not at starting and ending position")
	private String orgCode;

	private String createdBy;

	private String updatedBy;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public VisaDto() {
		super();
	}

	public VisaDto( String countryCode, String visaNumber, LocalDate validDate, String visaFile, String orgCode, String createdBy, String updatedBy, LocalDateTime createdAt,LocalDateTime updatedAt) {
		super();
		this.countryCode = countryCode;
		this.visaNumber = visaNumber;
		this.validDate = validDate;
		this.visaFile = visaFile;
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

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getVisaNumber() {
		return visaNumber;
	}

	public void setVisaNumber(String visaNumber) {
		this.visaNumber = visaNumber;
	}

	public LocalDate getValidDate() {
		return validDate;
	}

	public void setValidDate(LocalDate validDate) {
		this.validDate = validDate;
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

	public String getVisaFile() {
		return visaFile;
	}

	public void setVisaFile(String visaFile) {
		this.visaFile = visaFile;
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
