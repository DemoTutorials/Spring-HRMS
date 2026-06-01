package com.avisys.empmgmt.controller;

 

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avisys.empmgmt.dto.ColumnMetadata;
import com.avisys.empmgmt.dto.ColumnType;
import com.avisys.empmgmt.dto.DataTableMetadata;

 

@RestController
@RequestMapping("data-table-metadata")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DataTableMetadataController {
private static final DataTableMetadata ROLE_DEFINATION_METADATA;
private static final DataTableMetadata DESIGNATION_DEFINATION_METADATA;
private static final DataTableMetadata GRADE_DEFINATION_METADATA;
private static final DataTableMetadata EmployeeType_TYPE_DEFINITION_METADATA;
private static final DataTableMetadata Department_TYPE_DEFINITION_METADATA;
private static final DataTableMetadata Division_TYPE_DEFINITION_METADATA;
private static final DataTableMetadata Employee_Info_DEFINITION_METADATA;
private static final DataTableMetadata Organization_DEFINITION_METADATA;
private static final DataTableMetadata Common_Master_Parent_METADATA;
private static final DataTableMetadata Common_Master_Child_METADATA;
private static final DataTableMetadata Visa_DEFINATION_METADATA;
private static final DataTableMetadata Emergency_Contact_METADATA;
private static final DataTableMetadata EDUCATIONAL_QUALIFICATIONS_DEFINATION_METADATA;
private static final DataTableMetadata Work_Experience_DEFINITION_METADATA;
private static final DataTableMetadata Certification_Defination_METADATA;

    static {
        ROLE_DEFINATION_METADATA=new DataTableMetadata()
                .addColumnMetadata(new ColumnMetadata("", "id", "id", ColumnType.RADIO, 05))
                .addColumnMetadata(new ColumnMetadata("Role ID", "roleId","roleId" , ColumnType.TEXT, 31))
                .addColumnMetadata(new ColumnMetadata("Role Name", "roleName","roleName" , ColumnType.TEXT, 32))
                .addColumnMetadata(new ColumnMetadata("Org Code","orgCode","orgCode",ColumnType.TEXT,32));

 

        DESIGNATION_DEFINATION_METADATA=new DataTableMetadata()
                .addColumnMetadata(new ColumnMetadata("", "id", "id", ColumnType.RADIO, 05))
                .addColumnMetadata(new ColumnMetadata("Designation ID", "designationId","designationId" , ColumnType.TEXT, 21))
                .addColumnMetadata(new ColumnMetadata("Designation Name", "designationName","designationName" , ColumnType.TEXT, 21))
                .addColumnMetadata(new ColumnMetadata("Designation Description","designationDesc","designationDesc",ColumnType.TEXT,32))
                .addColumnMetadata(new ColumnMetadata("Org Code","orgCode","orgCode",ColumnType.TEXT,21));

 

        GRADE_DEFINATION_METADATA=new DataTableMetadata()
                .addColumnMetadata(new ColumnMetadata("", "id", "id", ColumnType.RADIO, 05))
                .addColumnMetadata(new ColumnMetadata("Grade ID", "gradeId","gradeId" , ColumnType.TEXT, 23))
                .addColumnMetadata(new ColumnMetadata("Grade Name", "gradeName","gradeName" , ColumnType.TEXT, 26))
                .addColumnMetadata(new ColumnMetadata("Grade Type","gradeType","gradeType",ColumnType.TEXT,23))
                .addColumnMetadata(new ColumnMetadata("Org Code","orgCode","orgCode",ColumnType.TEXT,23));

 

        EmployeeType_TYPE_DEFINITION_METADATA = new DataTableMetadata()
                .addColumnMetadata(new ColumnMetadata("", "id","id" ,ColumnType.RADIO, 05))
                .addColumnMetadata(new ColumnMetadata("Employee Type ID", "employeeTypeId","employeeTypeId", ColumnType.TEXT, 31))
                .addColumnMetadata(new ColumnMetadata("Type", "type","type", ColumnType.TEXT, 32))
                .addColumnMetadata(new ColumnMetadata("Org Code", "orgCode","orgCode", ColumnType.TEXT, 32));

 

        Department_TYPE_DEFINITION_METADATA = new DataTableMetadata()
                .addColumnMetadata(new ColumnMetadata("", "id","id" ,ColumnType.RADIO, 05))
                .addColumnMetadata(new ColumnMetadata("Department ID", "departmentId","departmentId", ColumnType.TEXT, 21))
                .addColumnMetadata(new ColumnMetadata("Department Name", "departmentName","departmentName", ColumnType.TEXT, 21))
                .addColumnMetadata(new ColumnMetadata("Department Description", "departmentDescription","departmentDescription", ColumnType.TEXT, 32))
                .addColumnMetadata(new ColumnMetadata("Org Code", "orgCode","orgCode", ColumnType.TEXT, 21));

 

        Division_TYPE_DEFINITION_METADATA = new DataTableMetadata()
                .addColumnMetadata(new ColumnMetadata("", "id","id" ,ColumnType.RADIO, 05))
                .addColumnMetadata(new ColumnMetadata("Division ID", "divisionId","divisionId", ColumnType.TEXT, 21))
                .addColumnMetadata(new ColumnMetadata("Division Name", "divisionName","divisionName", ColumnType.TEXT, 21))
                .addColumnMetadata(new ColumnMetadata("Division Description", "divisionDescription","divisionDescription", ColumnType.TEXT, 32))
                .addColumnMetadata(new ColumnMetadata("Org Code", "orgCode","orgCode", ColumnType.TEXT, 21));

        Employee_Info_DEFINITION_METADATA = new DataTableMetadata()
                .addColumnMetadata(new ColumnMetadata("", "id","id" ,ColumnType.RADIO, 05))
                .addColumnMetadata(new ColumnMetadata("Employee Code", "employeeCode","employeeCode", ColumnType.TEXT, 31))
                .addColumnMetadata(new ColumnMetadata("Full Name", "fullName","firstName", ColumnType.TEXT, 32))
                .addColumnMetadata(new ColumnMetadata("Division", "division.divisionName","divisionName", ColumnType.TEXT, 32));

        Organization_DEFINITION_METADATA = new DataTableMetadata()
                .addColumnMetadata(new ColumnMetadata("", "id","id" ,ColumnType.RADIO, 05))
                .addColumnMetadata(new ColumnMetadata("Organization Code", "organizationCode","organizationCode", ColumnType.TEXT, 31))
                .addColumnMetadata(new ColumnMetadata("Organization Name", "organizationName","organizationName", ColumnType.TEXT, 32))
                .addColumnMetadata(new ColumnMetadata("Organization Description", "organizationDesc","organizationDesc", ColumnType.TEXT, 32));
   
        Common_Master_Parent_METADATA=new DataTableMetadata()
                .addColumnMetadata(new ColumnMetadata("", "id", "id", ColumnType.RADIO, 05))
                .addColumnMetadata(new ColumnMetadata("Name", "masterName","masterName" , ColumnType.TEXT, 25))
                .addColumnMetadata(new ColumnMetadata("Code", "code","code" , ColumnType.TEXT, 25))
                .addColumnMetadata(new ColumnMetadata("Value","value","value",ColumnType.TEXT,25))
                .addColumnMetadata(new ColumnMetadata("Master","master","master",ColumnType.TEXT,20));

        Common_Master_Child_METADATA=new DataTableMetadata()
                .addColumnMetadata(new ColumnMetadata("", "id", "id", ColumnType.RADIO, 05))
                .addColumnMetadata(new ColumnMetadata("Name", "masterName","masterName" , ColumnType.TEXT, 25))
                .addColumnMetadata(new ColumnMetadata("Code", "code","code" , ColumnType.TEXT, 25))
                .addColumnMetadata(new ColumnMetadata("Value","value","value",ColumnType.TEXT, 25))
                .addColumnMetadata(new ColumnMetadata("Priority","priority","priority",ColumnType.TEXT, 20));

        Visa_DEFINATION_METADATA = new DataTableMetadata()
                .addColumnMetadata(new ColumnMetadata("", "id","id" ,ColumnType.RADIO, 05))
                .addColumnMetadata(new ColumnMetadata("Visa Number", "visaNumber","visaNumber" ,ColumnType.TEXT, 31))
                .addColumnMetadata(new ColumnMetadata("Country Code", "countryCode","countryCode" ,ColumnType.TEXT, 32))
                .addColumnMetadata(new ColumnMetadata("Valid Date", "validDate","validDate" ,ColumnType.TEXT, 32));
        
        Emergency_Contact_METADATA = new DataTableMetadata()
                .addColumnMetadata(new ColumnMetadata("", "id","id" ,ColumnType.RADIO, 05))
                .addColumnMetadata(new ColumnMetadata("Contact Number", "emergencyContactNumber","emergencyContactNumber" ,ColumnType.TEXT, 31))
                .addColumnMetadata(new ColumnMetadata("Contact Name", "emergencyContactName","emergencyContactName" ,ColumnType.TEXT, 32))
                .addColumnMetadata(new ColumnMetadata("Relation", "relation","relation" ,ColumnType.TEXT, 32));
        
        EDUCATIONAL_QUALIFICATIONS_DEFINATION_METADATA = new DataTableMetadata()
                .addColumnMetadata(new ColumnMetadata("", "id","id" ,ColumnType.RADIO, 05))
                .addColumnMetadata(new ColumnMetadata("School/University", "instituteName","instituteName" ,ColumnType.TEXT, 25))
                .addColumnMetadata(new ColumnMetadata("Qualification Level", "qualificationLevel","qualificationLevel" ,ColumnType.TEXT, 25))
                .addColumnMetadata(new ColumnMetadata("Educational Qualification", "educationalQualification","educationalQualification" ,ColumnType.TEXT, 25))
               .addColumnMetadata(new ColumnMetadata("Year Of Passing", "passingYear","passingYear" ,ColumnType.TEXT, 20));

        Work_Experience_DEFINITION_METADATA = new DataTableMetadata()
                .addColumnMetadata(new ColumnMetadata("", "id","id" ,ColumnType.RADIO, 05))
                .addColumnMetadata(new ColumnMetadata("Company", "companyName","companyName", ColumnType.TEXT, 15))
                .addColumnMetadata(new ColumnMetadata("Designation ", "designation","designation", ColumnType.TEXT, 15))
                .addColumnMetadata(new ColumnMetadata("From Date", "fromDate","fromDate", ColumnType.TEXT, 15))
                .addColumnMetadata(new ColumnMetadata("To Date", "toDate","toDate", ColumnType.TEXT, 15))
                .addColumnMetadata(new ColumnMetadata("Address", "address","address", ColumnType.TEXT, 30));
        
        Certification_Defination_METADATA = new DataTableMetadata()
        		.addColumnMetadata(new ColumnMetadata("", "id","id" ,ColumnType.RADIO, 05))
        		.addColumnMetadata(new ColumnMetadata("Certification", "certification","certification" ,ColumnType.TEXT, 30))
                .addColumnMetadata(new ColumnMetadata("Issued By", "issuedBy","issuedBy" ,ColumnType.TEXT, 35))
                .addColumnMetadata(new ColumnMetadata("Date Of Certification", "dateOfCertification","dateOfCertification" ,ColumnType.TEXT, 30));
    }

    @GetMapping("role")
    public DataTableMetadata getRoleDataTableMetadata() {
        return ROLE_DEFINATION_METADATA;
    }

    @GetMapping("grade")
    public DataTableMetadata getGradeDataTableMetadata() {
        return GRADE_DEFINATION_METADATA;
    }

    @GetMapping("designation")
    public DataTableMetadata getDesignationDataTableMetadata() {
        return DESIGNATION_DEFINATION_METADATA;
    }

    @GetMapping("employeeType-type")
    public DataTableMetadata getEmployeeTypeDataTableMetadata() {
        return EmployeeType_TYPE_DEFINITION_METADATA;
    }

    @GetMapping("department")
    public DataTableMetadata getDepartmentDataTableMetadata() {
        return Department_TYPE_DEFINITION_METADATA;
    }

    @GetMapping("division")
    public DataTableMetadata getDivisionDataTableMetadata() {
        return Division_TYPE_DEFINITION_METADATA;
    }

    @GetMapping("organization")
    public DataTableMetadata getOrganizationDataTableMetadata() {
        return Organization_DEFINITION_METADATA;
    }

    @GetMapping("employee")
    public DataTableMetadata getEmployeeDataTableMetadata() {
        return Employee_Info_DEFINITION_METADATA;
    }

    @GetMapping("parent-common-master")
    public DataTableMetadata getCommonMasterParentMetadata() {
        return Common_Master_Parent_METADATA;
    }
    
    @GetMapping("child-common-master")
    public DataTableMetadata getCommonMasterChildMetadata() {
        return Common_Master_Child_METADATA;
    } 
    
    @GetMapping("visa")
    public DataTableMetadata getVisaDetailMetadata() {
        return Visa_DEFINATION_METADATA;
    }
    
    @GetMapping("emergency-contact")
    public DataTableMetadata getEmergencyContactsMetadata() {
        return Emergency_Contact_METADATA;
    }
    
    @GetMapping("educational-qualification")
    public DataTableMetadata getEducationalQualificationMetadata() {
        return  EDUCATIONAL_QUALIFICATIONS_DEFINATION_METADATA;
    }
    
    @GetMapping("certification")
    public DataTableMetadata getCertificationMetadata() {
    	return  Certification_Defination_METADATA;
    }
    
    @GetMapping("work-experience")
    public DataTableMetadata getWorkExperienceMetadata() {
        return  Work_Experience_DEFINITION_METADATA;
   
    }
}