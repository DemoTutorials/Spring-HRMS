package com.avisys.empmgmt.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name="joining_detail")
public class JoiningDetail extends Status{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "joining_detail_generator")
	@SequenceGenerator(name="joining_detail_generator", sequenceName = "joining_detail_seq", initialValue = 1, allocationSize = 1)
	private Long id;
	
	@Column(name="confirmation_date")
	private LocalDate confirmationDate;
	
	@Column(name="notice_period")
	private int noticePeriod;
	
	@Column(name="resignation_date")
	private LocalDate resignationDate;
	
	@Column(name="relieving_date")
	private LocalDate relievingDate;
	  
    @OneToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

	public JoiningDetail() {
		super();
	}

	public JoiningDetail(String orgCode,boolean isDeleted, LocalDateTime createdAt, LocalDateTime updatedAt, 
			String createdBy, String updatedBy,Long id, LocalDate confirmationDate, int noticePeriod, LocalDate resignationDate,
			LocalDate relievingDate,Employee employee) {
		super(orgCode, isDeleted, createdAt, updatedAt, createdBy, updatedBy);
		this.id = id;
		this.confirmationDate = confirmationDate;
		this.noticePeriod = noticePeriod;
		this.resignationDate = resignationDate;
		this.relievingDate = relievingDate;
		this.employee = employee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getConfirmationDate() {
		return confirmationDate;
	}

	public void setConfirmationDate(LocalDate confirmationDate) {
		this.confirmationDate = confirmationDate;
	}

	public int getNoticePeriod() {
		return noticePeriod;
	}

	public void setNoticePeriod(int noticePeriod) {
		this.noticePeriod = noticePeriod;
	}

	public LocalDate getResignationDate() {
		return resignationDate;
	}

	public void setResignationDate(LocalDate resignationDate) {
		this.resignationDate = resignationDate;
	}

	public LocalDate getRelievingDate() {
		return relievingDate;
	}

	public void setRelievingDate(LocalDate relievingDate) {
		this.relievingDate = relievingDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
