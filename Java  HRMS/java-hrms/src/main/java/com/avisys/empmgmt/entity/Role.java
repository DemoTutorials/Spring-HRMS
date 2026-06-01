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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Role")
public class Role extends Status{

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
	@SequenceGenerator(sequenceName = "role_seq", name = "role_seq", initialValue = 1, allocationSize = 1)
	private long id;

	@NotBlank(message="Role Id should not be Blank")
	@NotNull(message="Role Id should not be null")
	@Column(name = "role_id")
	private String roleId;

	@NotBlank(message="Role name should not be Blank")
	@NotNull(message="Role name should not be null")
	@Column(name = "name")
	private String roleName;

	public Role(String roleId, String roleName, String orgCode, boolean isDeleted, LocalDateTime createdAt,
			LocalDateTime updatedAt, String createdBy, String updatedBy) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.orgCode = orgCode;
		this.isDeleted = isDeleted;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
	}
	
	public Role() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
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

}
