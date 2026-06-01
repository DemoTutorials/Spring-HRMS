package com.avisys.empmgmt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UpdateDTO {

	@NotNull(message = "Id cant be NULL")
	private long id;

	@NotNull(message = "Role Id field is required")
	@NotBlank(message = "Role Id cant be blank")
	private String roleId;

	@NotNull
	@NotBlank(message = "Role Name cant be blank")
	private String roleName;

	@NotNull
	@NotBlank(message = "Organization Code  cant be blank")
	private String orgCode;

	
	private String updatedBy;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public UpdateDTO(String roleId, String roleName, String orgCode, String updatedBy) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.orgCode = orgCode;
		this.updatedBy = updatedBy;
	}

	public UpdateDTO() {
		super();
	}


}
