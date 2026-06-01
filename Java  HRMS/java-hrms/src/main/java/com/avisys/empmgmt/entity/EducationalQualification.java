package com.avisys.empmgmt.entity;

import java.time.LocalDate;

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
public class EducationalQualification extends Status{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "education_id_squenece_generator")
	@SequenceGenerator(name="education_id_squenece_generator", initialValue = 1, allocationSize = 1)
	@Column(name="id")
	private Long id;
	
	@Column(name="educational_qualification")
	@NotNull(message="Qualification must not be null")
	@NotBlank(message="Qualification must not be blank")
	private String educationalQualification;
	
	@Column(name="institute_name")
	@NotNull(message="Name must not be null")
	@NotBlank(message="Name must not be blank")
	private String instituteName;
	
	@Column(name="department_name")
	@NotNull(message="Name must not be null")
	@NotBlank(message="Name must not be blank")
	private String qualificationLevel;
	
	@Column(name="passing_year")
	@NotNull(message="year must not be null")
	private LocalDate passingYear;
	
	@ManyToOne
	@JoinColumn(name = "employee_id_fk")
	private Employee employee;

	public EducationalQualification() {
		super();
	}

	public EducationalQualification(String educationalQualification,String instituteName,String qualificationLevel,LocalDate passingYear, Employee employee) {
		super();
		this.educationalQualification = educationalQualification;
		this.instituteName = instituteName;
		this.qualificationLevel = qualificationLevel;
		this.passingYear = passingYear;
		this.employee = employee;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEducationalQualification() {
		return educationalQualification;
	}

	public void setEducationalQualification(String educationalQualification) {
		this.educationalQualification = educationalQualification;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getQualificationLevel() {
		return qualificationLevel;
	}

	public void setQualificationLevel(String qualificationLevel) {
		this.qualificationLevel = qualificationLevel;
	}

	public LocalDate getPassingYear() {
		return passingYear;
	}

	public void setPassingYear(LocalDate passingYear) {
		this.passingYear = passingYear;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
