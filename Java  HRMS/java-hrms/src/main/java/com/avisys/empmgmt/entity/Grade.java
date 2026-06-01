package com.avisys.empmgmt.entity;

import java.time.LocalDateTime;

import com.avisys.empmgmt.dto.GradeDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Entity
public class Grade extends Status{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Id_sequence_generator")
	@SequenceGenerator(name = "Id_sequence_generator", initialValue = 1, allocationSize = 1)
	private Long id;

	@NotBlank(message = "Grade Id is required")
	@NotNull(message = "Grade Id is not null")
	@Column(name = "grade_id", unique = true)
	private String gradeId;

	@NotBlank(message = "Grade Name is required")
	@NotNull(message = "Grade Name  is not null")
	@Column(name = "name")
	private String gradeName;

	@NotBlank(message = "Grade Type is required")
	@NotNull(message = "Grade Type  is not null")
	@Column(name = "type")
	private String gradeType;

	public Grade() {
		super();
	}

	public Grade(Long id, String gradeId, String gradeName, String gradeType, String orgCode, boolean isDeleted, LocalDateTime createdAt, LocalDateTime updatedAt, String createdBy, String updatedBy) {
		super();
		this.id = id;
		this.gradeId = gradeId;
		this.gradeName = gradeName;
		this.gradeType = gradeType;
		this.orgCode = orgCode;
		this.isDeleted = isDeleted;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
	}

	public Grade(GradeDTO gradeDtoObject) {
		this.id = gradeDtoObject.getId();
		this.gradeId = gradeDtoObject.getGradeId();
		this.gradeName = gradeDtoObject.getGradeName();
		this.gradeType = gradeDtoObject.getGradeType();
		this.orgCode = gradeDtoObject.getOrgCode();

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


}
