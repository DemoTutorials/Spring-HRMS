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
@Table(name = "employee_type")
public class EmployeeType extends Status {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emptype_generator")
	@SequenceGenerator(name = "emptype_generator", sequenceName = "emptype_seq", allocationSize = 1)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "employee_type_id")
	@NotNull(message="Employee Type Id should not be null")
	@NotBlank(message="Employee Type Id should not be blank")
	private String employeeTypeId;

	@NotBlank(message = "Type Should not be blank")
	@NotNull(message = "Type Should not be null")
    @Column(name = "Type", nullable = false)
	private String type;

	public EmployeeType() {
        super();

    }

    public EmployeeType(Long id,String employeeTypeId, String orgCode, boolean isDeleted, LocalDateTime createdAt, LocalDateTime updatedAt, String createdBy, String updatedBy) {
        super();
        this.id = id;
        this.employeeTypeId = employeeTypeId;
        this.orgCode = orgCode;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
    
	public Long getId() {
		return id;
	}

	public String getEmployeeTypeId() {
		return employeeTypeId;
	}

	public void setEmployeeTypeId(String employeeTypeId) {
		this.employeeTypeId = employeeTypeId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
