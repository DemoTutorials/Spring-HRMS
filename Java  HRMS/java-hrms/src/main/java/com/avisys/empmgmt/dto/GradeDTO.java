package com.avisys.empmgmt.dto;

import java.time.LocalDateTime;

import com.avisys.empmgmt.entity.Grade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class GradeDTO {

	private Long id;

	@NotNull(message = "Grade Id is not null")
	@NotBlank(message = "Grade Id is required")
	private String gradeId;

	@NotNull(message = "Grade Name  is not null")
	@NotBlank(message = "Grade Name is required")
	private String gradeName;

	@NotNull(message = "Grade Name is not null")
	@NotBlank(message = "Grade Name is required")
	private String gradeType;
	
    @Pattern(regexp = "^[A-Za-z\\d][A-Za-z\\d-_]*[A-Za-z\\d]$",message = "Organization code should not contain any special characters except hypen,underscore but should not at starting and ending position")
	private String orgCode;
	
	private String createdBy;

	private String updatedBy;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;

	public GradeDTO() {
		super();
	}

	public GradeDTO(Long id, String gradeId, String gradeName, String gradeType, String orgCode, String createdBy, String updatedBy,LocalDateTime createdAt,LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.gradeId = gradeId;
		this.gradeName = gradeName;
		this.gradeType = gradeType;
		this.orgCode = orgCode;
		this.createdBy=createdBy;
		this.updatedBy=updatedBy;
		this.createdAt=createdAt;
		this.updatedAt=updatedAt;
	}

	public GradeDTO(Grade gradeObject) {
		this.id = gradeObject.getId();
		this.gradeId = gradeObject.getGradeId();
		this.gradeName = gradeObject.getGradeName();
		this.gradeType = gradeObject.getGradeType();
		this.orgCode = gradeObject.getOrgCode();
		this.createdBy=gradeObject.getCreatedBy();
		this.updatedBy=gradeObject.getUpdatedBy();
		this.createdAt=gradeObject.getCreatedAt();
		this.updatedAt=gradeObject.getUpdatedAt();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getGradeType() {
		return gradeType;
	}

	public void setGradeType(String gradeType) {
		this.gradeType = gradeType;
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
