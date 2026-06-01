package com.avisys.empmgmt.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.validation.constraints.Pattern;


public class PersonalDetailsDTO{

	private Long id;

    private String maritalStatus;
	
	private String bloodGroup;

    private String familyBackground;
    
    private String healthDetails;
    
    private String cprNumber;
    
    private String gosi;

    private String aadhaarNumber;
    
    private String aadhaarName;
    
    private String aadhaarFile;
    
    private String passportNumber;
    
    private String passportName;
    
    private LocalDate passportIssueDate;
    
    private LocalDate passportValidity;

    private String passportFile;
    
    private String panCardNumber;
    
    private String panCardName;
	
    private String panCardFile;
    
    private String bankAccountNumber;
    
    private String bankName;
    
    private String ifscCode;

    private String uanNumber;

    private String pfNumber;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
	
	private String createdBy;
	
	private String updatedBy;
	
    @Pattern(regexp = "^[A-Za-z\\d][A-Za-z\\d-_]*[A-Za-z\\d]$",message = "Organization code should not contain any special characters except hypen,underscore but should not at starting and ending position")
	private String orgCode;
	
	public PersonalDetailsDTO(String orgCode, LocalDateTime createdAt, LocalDateTime updatedAt,
			String createdBy, String updatedBy,String maritalStatus,String familyBackground,String healthDetails,String cprNumber,String gosi,String aadhaarNumber,String aadhaarName,
			String aadhaarFile,String passportNumber, String passportName,LocalDate passportIssueDate, LocalDate passportValidity, String passportFile, String panCardNumber,String panCardName,
			String panCardFile,String bankAccountNumber,String bankName,String ifscCode, String uanNumber,String pfNumber, String bloodGroup) {
		this.orgCode = orgCode;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.updatedBy = updatedBy;
		this.createdBy = createdBy;
		this.maritalStatus = maritalStatus;
		this.familyBackground = familyBackground;
		this.healthDetails = healthDetails;
		this.cprNumber = cprNumber;
		this.gosi = gosi;
		this.aadhaarNumber = aadhaarNumber;
		this.aadhaarName = aadhaarName;
		this.aadhaarFile = aadhaarFile;
		this.passportNumber = passportNumber;
		this.passportName = passportName;
		this.passportIssueDate = passportIssueDate;
		this.passportValidity = passportValidity;
		this.passportFile = passportFile;
		this.panCardNumber = panCardNumber;
		this.panCardName = panCardName;
		this.panCardFile = panCardFile;
		this.bankAccountNumber = bankAccountNumber;
		this.bankName = bankName;
		this.ifscCode = ifscCode;
		this.uanNumber = uanNumber;
		this.pfNumber = pfNumber;
		this.bloodGroup=bloodGroup;
	}
	
	public PersonalDetailsDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getFamilyBackground() {
		return familyBackground;
	}

	public void setFamilyBackground(String familyBackground) {
		this.familyBackground = familyBackground;
	}

	public String getHealthDetails() {
		return healthDetails;
	}

	public void setHealthDetails(String healthDetails) {
		this.healthDetails = healthDetails;
	}

	public String getCprNumber() {
		return cprNumber;
	}

	public void setCprNumber(String cprNumber) {
		this.cprNumber = cprNumber;
	}

	public String getGosi() {
		return gosi;
	}

	public void setGosi(String gosi) {
		this.gosi = gosi;
	}

	public String getAadhaarNumber() {
		return aadhaarNumber;
	}

	public void setAadhaarNumber(String aadhaarNumber) {
		this.aadhaarNumber = aadhaarNumber;
	}

	public String getAadhaarName() {
		return aadhaarName;
	}

	public void setAadhaarName(String aadhaarName) {
		this.aadhaarName = aadhaarName;
	}

	public String getAadhaarFile() {
		return aadhaarFile;
	}

	public void setAadhaarFile(String aadhaarFile) {
		this.aadhaarFile = aadhaarFile;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getPassportName() {
		return passportName;
	}

	public void setPassportName(String passportName) {
		this.passportName = passportName;
	}

	public LocalDate getPassportIssueDate() {
		return passportIssueDate;
	}

	public void setPassportIssueDate(LocalDate passportIssueDate) {
		this.passportIssueDate = passportIssueDate;
	}

	public LocalDate getPassportValidity() {
		return passportValidity;
	}

	public void setPassportValidity(LocalDate passportValidity) {
		this.passportValidity = passportValidity;
	}

	public String getPassportFile() {
		return passportFile;
	}

	public void setPassportFile(String passportFile) {
		this.passportFile = passportFile;
	}

	public String getPanCardNumber() {
		return panCardNumber;
	}

	public void setPanCardNumber(String panCardNumber) {
		this.panCardNumber = panCardNumber;
	}

	public String getPanCardName() {
		return panCardName;
	}

	public void setPanCardName(String panCardName) {
		this.panCardName = panCardName;
	}

	public String getPanCardFile() {
		return panCardFile;
	}

	public void setPanCardFile(String panCardFile) {
		this.panCardFile = panCardFile;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getUanNumber() {
		return uanNumber;
	}

	public void setUanNumber(String uanNumber) {
		this.uanNumber = uanNumber;
	}

	public String getPfNumber() {
		return pfNumber;
	}

	public void setPfNumber(String pfNumber) {
		this.pfNumber = pfNumber;
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

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	
	

}
