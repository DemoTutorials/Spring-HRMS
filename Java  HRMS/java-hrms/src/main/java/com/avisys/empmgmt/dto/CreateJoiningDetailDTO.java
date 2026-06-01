package com.avisys.empmgmt.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CreateJoiningDetailDTO {
	
	private LocalDate confirmationDate;
	
	private int noticePeriod;
	
	private LocalDate resignationDate;
	
	private LocalDate relievingDate;
    
	private String createdBy;
     
	@NotBlank(message = "Organisation Code should not be blank")
	@NotNull(message = "Organisation Code should not be null")
    @Pattern(regexp = "^[A-Za-z\\d][A-Za-z\\d-_]*[A-Za-z\\d]$",message = "Organization code should not contain any special characters except hypen,underscore but should not at starting and ending position")
	protected String orgCode;
    
	public CreateJoiningDetailDTO() {
		super();
	}

	public CreateJoiningDetailDTO(LocalDate confirmationDate,int noticePeriod,LocalDate resignationDate, LocalDate relievingDate,String createdBy) {
		super();
		this.confirmationDate = confirmationDate;
		this.noticePeriod = noticePeriod;
		this.resignationDate = resignationDate;
		this.relievingDate = relievingDate;
		this.createdBy = createdBy;
	}

	public LocalDate getConfirmationDate() {
		return confirmationDate;
	}

	public void setConfirmationDate(LocalDate confirmationDate) {
		this.confirmationDate = confirmationDate;
	}

	public int getNoticePeriod() {
		return noticePeriod;
	}

	public void setNoticePeriod(int noticePeriod) {
		this.noticePeriod = noticePeriod;
	}

	public LocalDate getResignationDate() {
		return resignationDate;
	}

	public void setResignationDate(LocalDate resignationDate) {
		this.resignationDate = resignationDate;
	}

	public LocalDate getRelievingDate() {
		return relievingDate;
	}

	public void setRelievingDate(LocalDate relievingDate) {
		this.relievingDate = relievingDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
}
