package com.avisys.empmgmt.entity;

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
import jakarta.validation.constraints.Size;

@Entity
@Table(name="address")
public class Address extends Status{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "address_id_squenece_generator")
	@SequenceGenerator(name="address_id_squenece_generator", initialValue = 1, allocationSize = 1)
	@Column(name="id")
	private Long id;
	
	@Column(name = "address_type")
	private String addressType;

	@Column(name = "address_1")
	private String address1;

	@Column(name = "address_2")
	private String address2;

	private String landmark;

	@Column(name="tenure_year")
	private int tenureYear;
	
	@Column(name="tenure_month")
	private int tenureMonth;
	
	private String city;
	
	private String state;

	private String country;

	@Column(name="post_code")
	private String postcode;
	
	@Column(name="ownership_status")
	private String ownershipStatus;
	
	@ManyToOne
	@JoinColumn(name = "employee_id_fk")
	private Employee employee;
	
	public Address() {
		super();
	}

	public Address(String orgCode, boolean isDeleted, LocalDateTime createdAt, LocalDateTime updatedAt, String createdBy, String updatedBy, String addressType,
			String address1, String address2, String landmark, int tenureYear, int tenureMonth, String city, String state, String country,String postcode,String ownershipStatus, Employee employee) {
		super(orgCode, isDeleted, createdAt, updatedAt, createdBy, updatedBy);
		this.addressType = addressType;
		this.address1 = address1;
		this.address2 = address2;
		this.landmark = landmark;
		this.tenureYear = tenureYear;
		this.tenureMonth=tenureMonth;
		this.city = city;
		this.state = state;
		this.country = country;
		this.postcode = postcode;
		this.ownershipStatus = ownershipStatus;
		this.employee = employee;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getOwnershipStatus() {
		return ownershipStatus;
	}

	public void setOwnershipStatus(String ownershipStatus) {
		this.ownershipStatus = ownershipStatus;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public int getTenureYear() {
		return tenureYear;
	}

	public void setTenureYear(int tenureYear) {
		this.tenureYear = tenureYear;
	}

	public int getTenureMonth() {
		return tenureMonth;
	}

	public void setTenureMonth(int tenureMonth) {
		this.tenureMonth = tenureMonth;
	}
	
	
}