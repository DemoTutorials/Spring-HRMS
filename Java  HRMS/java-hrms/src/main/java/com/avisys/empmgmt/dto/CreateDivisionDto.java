package com.avisys.empmgmt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateDivisionDto {
	@NotNull(message="Division Id should not be null")
	@NotBlank(message="Division Id should not be blank")
	private String divisionId;
    
	@NotNull(message="divisionName should not be null")
	@NotBlank(message="divisionName should not be blank")
	private String divisionName;
    
    @NotBlank(message = "Division Description should not be blank" )
	private String divisionDescription;
    
    @NotNull(message="Orgcode should not be null")
	@NotBlank(message="Orgcode should not be blank")
    private String orgCode;
    
    private String createdBy;
        
	public CreateDivisionDto() {
         super();
	}

	public CreateDivisionDto( String divisionId, String divisionName, String divisionDescription, String orgCode,
			String createdBy) {
		super();
		this.divisionId = divisionId;
		this.divisionName = divisionName;
		this.divisionDescription = divisionDescription;
		this.orgCode = orgCode;
		this.createdBy = createdBy;
	}



	public String getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public String getDivisionDescription() {
		return divisionDescription;
	}

	public void setDivisionDescription(String divisionDescription) {
		this.divisionDescription = divisionDescription;
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
