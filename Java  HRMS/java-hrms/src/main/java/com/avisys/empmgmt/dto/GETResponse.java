package com.avisys.empmgmt.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Pattern;

public class GETResponse {

	private long id;
	private String roleName;
	private String roleId;
	
    @Pattern(regexp = "^[A-Za-z\\d][A-Za-z\\d-_]*[A-Za-z\\d]$",message = "Organization code should not contain any special characters except hypen,underscore but should not at starting and ending position")
	private String orgCode;
	private String createdBy;
	private LocalDateTime created_at;
	private String updated_by;
	private LocalDateTime updated_at;
	
	
	
	public GETResponse(long id,String roleName, String roleId, String orgCode, String createdBy, LocalDateTime created_at,
			String updated_by, LocalDateTime updated_at) {
		super();
		this.id=id;
		this.roleName = roleName;
		this.roleId = roleId;
		this.orgCode = orgCode;
		this.createdBy = createdBy;
		this.created_at = created_at;
		this.updated_by = updated_by;
		this.updated_at = updated_at;
	}



	public GETResponse() {
		super();
	}



	public GETResponse(String roleName, String roleId, String orgCode, String createdBy, LocalDateTime created_at,
			String updated_by) {
		super();
		this.roleName = roleName;
		this.roleId = roleId;
		this.orgCode = orgCode;
		this.createdBy = createdBy;
		this.created_at = created_at;
		
		this.updated_by = updated_by;
	}



	public GETResponse(String roleName, String roleId, String createdBy, LocalDateTime created_at) {
		super();
		this.roleName = roleName;
		this.roleId = roleId;
		this.createdBy = createdBy;
		this.created_at = created_at;
	}



	public String getRoleName() {
		return roleName;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public LocalDateTime getUpdated_at() {
		return updated_at;
	}



	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}



	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}



	public String getRoleId() {
		return roleId;
	}



	public void setRoleId(String roleId) {
		this.roleId = roleId;
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



	public LocalDateTime getCreated_at() {
		return created_at;
	}



	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}



	public String getUpdated_by() {
		return updated_by;
	}



	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}
	
	
	
	
	
}
