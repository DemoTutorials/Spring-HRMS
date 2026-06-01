package com.avisys.empmgmt.dto;

import com.avisys.empmgmt.entity.Department;
import com.avisys.empmgmt.entity.Designation;
import com.avisys.empmgmt.entity.EmployeeType;
import com.avisys.empmgmt.entity.Grade;
import com.avisys.empmgmt.entity.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CreateCompanyDetailDTO {

	@NotBlank(message = "Company Email should not be blank")
	@NotNull(message = "Company Email should not be null")
	private String companyEmail;
	

	@NotNull(message = "Client Email should not be null")
	private String clientEmail;
	

	@NotBlank(message = "billable should not be blank")
	@NotNull(message = "billable should not be null")
	private String billable;


	@NotBlank(message = "shift should not be blank")
	@NotNull(message = "shift should not be null")
	private String shift;

	@NotBlank(message = "reviewer_manager should not be blank")
	@NotNull(message = "reviewer_manager should not be null")
	private String reviewerManager;
	
	@NotBlank(message = "reporting_manager should not be blank")
	@NotNull(message = "reporting_manager should not be null")
	private String reportingManager;
	
	@NotNull(message = "probation should not be null")
	private String probation;
	
	protected String createdBy;
	
	@Pattern(regexp = "^[a-zA-Z0-9-_]{1,50}$",message = "Organization code should not contain any special characters except hypen,underscore")
	protected String orgCode;
	
	private Designation designation;
	
	private Department department;
	
	private Grade grade;
	
	private Role role;
	
	private EmployeeType employeeType;

	public CreateCompanyDetailDTO() {
		super();
	}

	public CreateCompanyDetailDTO(String companyEmail,String clientEmail, String billable,String shift,String reviewerManager,
			String reportingManager,String probation, String createdBy, String orgCode,
			Designation designation, Department department, Grade grade, Role role, EmployeeType employeeType) {
		super();
		this.companyEmail = companyEmail;
		this.clientEmail = clientEmail;
		this.billable = billable;
		this.shift = shift;
		this.reviewerManager = reviewerManager;
		this.reportingManager = reportingManager;
		this.probation = probation;
		this.createdBy = createdBy;
		this.orgCode = orgCode;
		this.designation = designation;
		this.department = department;
		this.grade = grade;
		this.role = role;
		this.employeeType = employeeType;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public String getBillable() {
		return billable;
	}

	public void setBillable(String billable) {
		this.billable = billable;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public String getReviewerManager() {
		return reviewerManager;
	}

	public void setReviewerManager(String reviewerManager) {
		this.reviewerManager = reviewerManager;
	}

	public String getReportingManager() {
		return reportingManager;
	}

	public void setReportingManager(String reportingManager) {
		this.reportingManager = reportingManager;
	}

	public String getProbation() {
		return probation;
	}

	public void setProbation(String probation) {
		this.probation = probation;
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

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public EmployeeType getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}

}
