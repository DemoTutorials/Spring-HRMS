package com.avisys.empmgmt.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="common_masters")
public class CommonMaster{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "common_master_id_squenece_generator")
	@SequenceGenerator(name="common_master_id_squenece_generator", initialValue = 1, allocationSize = 1)
	private Long id;
	
	@NotNull(message="Master Name must not be null")
	@NotBlank(message="Master Name must not be blank")
	@Column(name="master_name")
	private String masterName;
	
	@NotNull(message="Code must not be null")
	@NotBlank(message="Code must not be blank")
	private String code;
	

	private int priority;
	
	@NotNull(message="Value must not be null")
	@NotBlank(message="Value must not be blank")
	private String value;
	
	@Column(name="is_master")
	private boolean isMaster;
	
	@Column(name="foreign_key")
	private String foreignKey;
	
	@Column(name="additional_value")
	private String additionalValue;
	
	@Column(name = "is_deleted")
	protected boolean isDeleted ;

	@Column(name = "created_at")
	protected LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	protected LocalDateTime updatedAt;
	
	@Column(name = "created_by")
	protected String createdBy;
	
	@Column(name = "updated_by")
	protected String updatedBy;


	public CommonMaster() {
		super();
	}

	public CommonMaster(Long id, String masterName,String code,int priority,String value,boolean isMaster, String foreignKey, String additionalValue, boolean isDeleted, LocalDateTime createdAt,LocalDateTime updatedAt, String createdBy, String updatedBy) {
		super();
		this.id = id;
		this.masterName = masterName;
		this.code = code;
		this.priority = priority;
		this.value = value;
		this.isMaster = isMaster;
		this.foreignKey = foreignKey;
		this.additionalValue = additionalValue;
		this.isDeleted = isDeleted;
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

	public String getForeignKey() {
		return foreignKey;
	}

	public void setForeignKey(String foreignKey) {
		this.foreignKey = foreignKey;
	}

	public String getAdditionalValue() {
		return additionalValue;
	}

	public void setAdditionalValue(String additionalValue) {
		this.additionalValue = additionalValue;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
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

	public void setMaster(boolean isMaster) {
		this.isMaster = isMaster;
	}

	public boolean isMaster() {
		return isMaster;
	}
	
}
