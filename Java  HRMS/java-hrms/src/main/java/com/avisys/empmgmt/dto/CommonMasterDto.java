package com.avisys.empmgmt.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CommonMasterDto {

	private Long id;
	
	@NotNull(message="Master Name must not be null")
	@NotBlank(message="Master Name must not be blank")
	private String masterName;
	
	@NotNull(message="Code must not be null")
	@NotBlank(message="Code must not be blank")
	private String code;
	
	private int priority;
	
	@NotNull(message="Value must not be null")
	@NotBlank(message="Value must not be blank")
	private String value;

	private boolean isMaster;

	private String foreignKey;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	private String createdBy;
	private String updatedBy;
	
	public CommonMasterDto() {
		super();
	}

	public CommonMasterDto(Long id,String masterName, String code,int priority, String value, boolean isMaster, String foreignKey, LocalDateTime createdAt, LocalDateTime updatedAt, String createdBy, String updatedBy) {
		super();
		this.id = id;
		this.masterName = masterName;
		this.code = code;
		this.priority = priority;
		this.value = value;
		this.isMaster = isMaster;
		this.foreignKey = foreignKey;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMasterName() {
		return masterName;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isMaster() {
		return isMaster;
	}

	public void setMaster(boolean isMaster) {
		this.isMaster = isMaster;
	}

	public String getForeignKey() {
		return foreignKey;
	}

	public void setForeignKey(String foreignKey) {
		this.foreignKey = foreignKey;
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
	
}
