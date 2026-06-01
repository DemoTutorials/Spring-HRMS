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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="visa")
public class Visa extends Status {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "visa_id_squenece_generator")
	@SequenceGenerator(name="visa_id_squenece_generator", initialValue = 1, allocationSize = 1)
	@Column(name="id")
	private Long id;
	
	@Column(name="country_code")
	@NotNull(message="countryCode field must not be null")
	@NotBlank(message="countryCode field must not be blank")
	private String countryCode;
	
	@Column(name="visa_number")
	@NotNull(message="visaNumber field must not be null")
	@NotBlank(message="visaNumber field must not be blank")
	private String visaNumber;

	@Column(name="valid_date")
	@NotNull(message="validDate field must not be null")
	private LocalDate validDate;
	
	@Column(name="visa_file")
	private String visaFile;
	
	@ManyToOne
	@JoinColumn(name = "employee_id_fk")
	private Employee employee;

	public Visa() {
		super();
	}

	public Visa( String orgCode, boolean isDeleted, LocalDateTime createdAt, LocalDateTime updatedAt, String createdBy, String updatedBy,String countryCode,String visaNumber, LocalDate validDate, String visaFile, Employee employee) {
		super(orgCode, isDeleted, createdAt, updatedAt, createdBy, updatedBy);
		this.countryCode = countryCode;
		this.visaNumber = visaNumber;
		this.validDate = validDate;
		this.visaFile = visaFile;
		this.employee = employee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getVisaNumber() {
		return visaNumber;
	}

	public void setVisaNumber(String visaNumber) {
		this.visaNumber = visaNumber;
	}

	public LocalDate getValidDate() {
		return validDate;
	}

	public void setValidDate(LocalDate validDate) {
		this.validDate = validDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getVisaFile() {
		return visaFile;
	}

	public void setVisaFile(String visaFile) {
		this.visaFile = visaFile;
	}

}
