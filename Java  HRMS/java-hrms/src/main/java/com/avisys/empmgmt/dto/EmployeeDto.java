package com.avisys.empmgmt.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.avisys.empmgmt.entity.Division;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EmployeeDto {

	private Long Id;
	
	@NotNull(message="employee code must not be null")
	@NotBlank(message="employee code must not be blank")
	@Size(max=8, message="Id should be at max 8 character")
	private String employeeCode;
	
	private String profileImage;
	
	@NotNull(message="title must not be null")
	@NotBlank(message="title must not be blank")
    private String title;
	
	@NotNull(message="First Name must not be null")
	@NotBlank(message="First Name must not be blank")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "First name should not contain digits and special characters")
	private String firstName;
	
	private String middleName;
	
	@NotNull(message="Last Name must not be null")
	@NotBlank(message="Last Name must not be blank")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Last name should not contain digits and special characters")
	private String lastName;
	
	@NotNull(message="Date must not be null")
	private LocalDate dateOfBirth;
	
	@NotNull(message="Gender must not be null")
	@NotBlank(message="Gender must not be blank")
	private String gender;
	
	@NotNull(message="Date must not be null")
	private LocalDate dateOfJoining;

	@NotNull(message="Age must not be null")
	@NotBlank(message="Age must not be blank")
	private String age;
	
	@NotNull(message="Status must not be null")
	@NotBlank(message="Status must not be blank")
	private String status;
	
	private Division division;
	
	private String userId;
	
	@NotNull(message="Mobile number must not be null")
	@NotBlank(message="Mobile number must not be blank")
	private String mobile;
	
	private String phone;
	
	@NotNull(message="Email Id must not be null")
	@NotBlank(message="Email Id must not be blank")
	private String email;
	
	@NotBlank(message = "Organisation Code should not be blank")
	@NotNull(message = "Organisation Code should not be null")
    @Pattern(regexp = "^[A-Za-z\\d][A-Za-z\\d-_]*[A-Za-z\\d]$",message = "Organization code should not contain any special characters except hypen,underscore but should not at starting and ending position")
	private String orgCode;

	private String createdBy;

	private String updatedBy;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public EmployeeDto() {
		super();
	}

	public EmployeeDto(Long id, String employeeCode, String profileImage,String title, String firstName, String middleName, String lastName, LocalDate dateOfBirth, String gender, LocalDate dateOfJoining, String age, String status, Division division, String userId, String mobile, String phone, String email, String orgCode, String createdBy, String updatedBy, LocalDateTime createdAt,LocalDateTime updatedAt) {
		super();
		Id = id;
		this.employeeCode = employeeCode;
		this.profileImage = profileImage;
		this.title = title;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.dateOfJoining = dateOfJoining;
		this.age = age;
		this.status = status;
		this.division = division;
		this.userId = userId;
		this.mobile = mobile;
		this.phone = phone;
		this.email = email;
		this.orgCode = orgCode;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdAt=createdAt;
		this.updatedAt=updatedAt;
	}


	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
	}


	public String getEmployeeCode() {
		return employeeCode;
	}


	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getMiddleName() {
		return middleName;
	}


	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}


	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}	
	
	
	public String getProfileImage() {
		return profileImage;
	}


	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}


	public String getFullName() {
	        return firstName + " " + lastName;
	    }

    public String getDivisionName() {
	        return division.getdivisionName();
	    }

}