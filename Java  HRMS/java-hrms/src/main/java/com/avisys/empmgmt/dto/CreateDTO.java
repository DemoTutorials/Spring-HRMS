package com.avisys.empmgmt.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CreateDTO {

	@NotNull(message = "")
	@NotBlank(message = "Role Id field cant be blank")
	private String roleId;
	
	@NotNull
	@NotBlank(message = "Role Name field cant be blank")
	private String roleName;
	
	@NotNull
	@NotBlank(message = "Organization Code field cant be blank")
	private String orgCode;
	
	@NotNull
	@NotBlank(message = "Created by field  cant be null")
	private String createdBy;

	public CreateDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreateDTO(
			@NotNull(message = "") @NotBlank(message = "Role Id field cant be blank") @Pattern(regexp = "^[a-zA-Z0-9]{1,50}$", message = "ID must starts with alphabets followed numbers & cannot contain special character") String roleId,
			@NotNull @NotBlank(message = "Role Name field cant be blank") @Pattern(regexp = "^[a-zA-Z-_ ]{1,100}$", message = "Name must starts with alphabets") String roleName,
			@NotNull @NotBlank(message = "Organization Code field cant be blank") @Pattern(regexp = "^[a-zA-Z-_]{1,10}$", message = "Organization code can't contain white spaces & special characters") String orgCode,
			@NotNull @NotBlank(message = "Created by field  cant be null") String createdBy) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.orgCode = orgCode;
		this.createdBy = createdBy;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	
	
}