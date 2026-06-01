package com.avisys.empmgmt.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CertificationDto {
	
	private Long id;
	
	@NotNull(message="certification field must not be null")
	@NotBlank(message="certification field must not be blank")
	private String certification;

	@NotNull(message="date of certification must not be null")
	private LocalDate dateOfCertification;
	
	@NotNull(message="issued by field must not be null")
	@NotBlank(message="issued by field must not be blank")
	private String issuedBy;
	
	@NotBlank(message = "Organisation Code should not be blank")
	@NotNull(message = "Organisation Code should not be null")
    @Pattern(regexp = "^[A-Za-z\\d][A-Za-z\\d-_]*[A-Za-z\\d]$",message = "Organization code should not contain any special characters except hypen,underscore but should not at starting and ending position")
	private String orgCode;
	
	
	private String createdBy;
	private String updatedBy;	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	

	public CertificationDto(Long id,
			@NotNull(message = "certification field must not be null") @NotBlank(message = "certification field must not be blank") String certification,
			@NotNull(message = "date of certification must not be null") LocalDate dateOfCertification,
			@NotNull(message = "issued by field must not be null") @NotBlank(message = "issued by field must not be blank") String issuedBy,
			@NotBlank(message = "Organisation Code should not be blank") @NotNull(message = "Organisation Code should not be null") @Pattern(regexp = "^[A-Za-z\\d][A-Za-z\\d-_]*[A-Za-z\\d]$", message = "Organization code should not contain any special characters except hypen,underscore but should not at starting and ending position") String orgCode,
			String createdBy, String updatedBy, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.certification = certification;
		this.dateOfCertification = dateOfCertification;
		this.issuedBy = issuedBy;
		this.orgCode = orgCode;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public CertificationDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCertification() {
		return certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public LocalDate getDateOfCertification() {
		return dateOfCertification;
	}

	public void setDateOfCertification(LocalDate dateOfCertification) {
		this.dateOfCertification = dateOfCertification;
	}

	public String getIssuedBy() {
		return issuedBy;
	}

	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
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


	public String getOrgCode() {
		return orgCode;
	}


	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
	
	
}
