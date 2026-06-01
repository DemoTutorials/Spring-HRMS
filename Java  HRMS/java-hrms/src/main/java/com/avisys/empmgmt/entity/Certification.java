package com.avisys.empmgmt.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Certification extends Status {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "certification_squenece_generator")
	@SequenceGenerator(name="certification_squenece_generator", initialValue = 1, allocationSize = 1)
	@Column(name="id")
	private Long id;
	
	@Column(name="certification")
	@NotNull(message="certification field must not be null")
	@NotBlank(message="certification field must not be blank")
	private String certification;
	
	@Column(name="date_of_certification")
	@NotNull(message="date of certification must not be null")
	private LocalDate dateOfCertification;
	
	@Column(name="issued_by")
	@NotNull(message="issued by field must not be null")
	@NotBlank(message="issued by field must not be blank")
	private String issuedBy;
	
	@ManyToOne
	@JoinColumn(name = "employee_id_fk")
	private Employee employee;

	public Certification() {
		super();
	}

	public Certification(String orgCode, boolean isDeleted, LocalDateTime createdAt, LocalDateTime updatedAt,
			String createdBy, String updatedBy, Long id,
			@NotNull(message = "certification field must not be null") @NotBlank(message = "certification field must not be blank") String certification,
			LocalDate dateOfCertification, String issuedBy, Employee employee) {
		super(orgCode, isDeleted, createdAt, updatedAt, createdBy, updatedBy);
		this.id = id;
		this.certification = certification;
		this.dateOfCertification = dateOfCertification;
		this.issuedBy = issuedBy;
		this.employee = employee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCertification() {
		return certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public LocalDate getDateOfCertification() {
		return dateOfCertification;
	}

	public void setDateOfCertification(LocalDate dateOfCertification) {
		this.dateOfCertification = dateOfCertification;
	}

	public String getIssuedBy() {
		return issuedBy;
	}

	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	

	
}
