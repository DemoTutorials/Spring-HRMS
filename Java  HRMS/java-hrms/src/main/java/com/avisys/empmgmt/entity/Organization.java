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
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "organization")
public class Organization  {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "org_seq")
	@SequenceGenerator(sequenceName = "org_seq", name = "org_seq", initialValue = 1, allocationSize = 1)
	private long id;

	@Column(name = "organization_code")
	//@NotBlank(message = "Organization Code should not be blank")
	//@NotNull(message = "Organization Code should not be null")
	private String organizationCode;
	
	@Column(name = "organization_name")
	//@NotBlank(message = "Organization Name should not be blank")
	//@NotNull(message = "Organization Name should not be null")
	private String organizationName;
	
	@Column(name = "organization_description")
	//@NotNull(message = "Organization Description should not be null")
	private String organizationDesc;
	
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
	
	

	public Organization() {
		super();
	}


		public Organization(String organizationCode,
			@NotBlank(message = "Organization Name should not be blank") @NotNull(message = "Organization Name should not be null") String organizationName,
			@NotNull(message = "Organization Description should not be null") String organizationDesc,
			boolean isDeleted, LocalDateTime createdAt, LocalDateTime updatedAt, String createdBy, String updatedBy) {
		super();
		this.organizationCode = organizationCode;
		this.organizationName = organizationName;
		this.organizationDesc = organizationDesc;
		this.isDeleted = isDeleted;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
	}


		public long getId() {
			return id;
		}


		public void setId(long id) {
			this.id = id;
		}


		public String getOrganizationCode() {
			return organizationCode;
		}


		public void setOrganizationCode(String organizationCode) {
			this.organizationCode = organizationCode;
		}


		public String getOrganizationName() {
			return organizationName;
		}


		public void setOrganizationName(String organizationName) {
			this.organizationName = organizationName;
		}


		public String getOrganizationDesc() {
			return organizationDesc;
		}


		public void setOrganizationDesc(String organizationDesc) {
			this.organizationDesc = organizationDesc;
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
		




















	

	


	
	
}
