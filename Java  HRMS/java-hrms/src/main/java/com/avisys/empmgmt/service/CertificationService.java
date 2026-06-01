package com.avisys.empmgmt.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Pageable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avisys.empmgmt.dto.CertificationDto;
import com.avisys.empmgmt.dto.WorkExperienceDto;
import com.avisys.empmgmt.entity.Certification;
import com.avisys.empmgmt.entity.Employee;
import com.avisys.empmgmt.entity.WorkExperience;
import com.avisys.empmgmt.exception.EmployeeException;
import com.avisys.empmgmt.exception.WorkExperienceException;
import com.avisys.empmgmt.repository.CertificationRepository;
import com.avisys.empmgmt.repository.EmployeeRepo;
import com.avisys.empmgmt.repository.WorkExperienceRepository;

import jakarta.validation.Valid;

@Service
@Transactional
public class CertificationService {
	@Autowired
	private CertificationRepository certificationRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private EmployeeRepo employeeRepository;

	public CertificationDto addCertification(@Valid CertificationDto certificationDto, Long employeeId) {
		
		Employee employee=this.employeeRepository.findByIdAndIsDeletedFalse(employeeId).orElseThrow(()-> new EmployeeException("Employee not found"));
		
		Certification certification = this.modelMapper.map(certificationDto, Certification.class);
		certification.setCreatedAt(LocalDateTime.now());
		certification.setDeleted(false);
		certification.setEmployee(employee);
		certification.setUpdatedBy(null);
		Certification certificationObject = certificationRepository.save(certification);
		
		return  this.modelMapper.map(certificationObject, CertificationDto.class);

}

	public List<CertificationDto> getCertificationByEmployee(Long employeeId) {
		Employee employee = employeeRepository.findByIdAndIsDeletedFalse(employeeId).orElseThrow(()->new EmployeeException("Employee not found"));		
		
		List<Certification> certification=certificationRepository.findByEmployeeAndIsDeletedFalse(employee);
		List<CertificationDto> certificationDto = certification.stream().map((certi)-> this.modelMapper.map(certi,CertificationDto.class)).collect(Collectors.toList());
		
		return certificationDto;
	}
	

    public CertificationDto getByEmployeeIdAndCertificateId(Long employeeId, Long certificationId) {
        Employee employee = employeeRepository.findByIdAndIsDeletedFalse(employeeId).orElseThrow(()->new EmployeeException("Employee not found"));
        Certification certificate=certificationRepository.findByIdAndIsDeletedFalse(certificationId).orElseThrow(()->new EmployeeException("certificate not found"));

        return this.modelMapper.map(certificate,CertificationDto.class);

    }


	public CertificationDto updateCertification(CertificationDto certificationDto, Long employeeId) {
		Employee employee=employeeRepository.findByIdAndIsDeletedFalse(employeeId).orElseThrow(()->new EmployeeException("Employee not found"));

		Certification certification = certificationRepository.findByIdAndIsDeletedFalse(certificationDto.getId()).orElseThrow(()->new EmployeeException("Certification not found"));
	    if(employee==certification.getEmployee()) {
	    	
	    	certificationDto.setCreatedBy(certificationDto.getCreatedBy());
	    	certificationDto.setCreatedAt(certificationDto.getCreatedAt());
	        modelMapper.map(certificationDto, certification);
	        
	        certification.setUpdatedAt(LocalDateTime.now());
	        certification.setUpdatedBy(certificationDto.getUpdatedBy());
	        certification.setDeleted(false);

	        Certification certificationObject = certificationRepository.save(certification);
	 
	    return  this.modelMapper.map(certificationObject, CertificationDto.class);
	    } else throw new WorkExperienceException("EmployeeId doesn't have this certification Id");
	}

	public String deleteCertification(Long employeeId, Long certificationId,String updatedBy) {
		Employee employee=this.employeeRepository.findByIdAndIsDeletedFalse(employeeId).orElseThrow(()->new EmployeeException("Employee not found"));
		Certification certification = this.certificationRepository.findByIdAndIsDeletedFalse(certificationId).orElseThrow(()->new EmployeeException("Certification not found"));
			
		if(employee==certification.getEmployee()) {
			certification.setDeleted(true);
			certification.setUpdatedAt(LocalDateTime.now());
			certification.setUpdatedBy(updatedBy);
			certificationRepository.save(certification); 
			return "Certification deleted successfully";
		}else throw new WorkExperienceException("EmployeeId doesn't match with certificationId");
	}
	
	public Page<CertificationDto> searchCertification(Pageable pageable, String keyword, Long employeeId) {
        Employee employee = this.employeeRepository.findByIdAndIsDeletedFalse(employeeId)
                .orElseThrow(() -> new EmployeeException("Employee not found"));

        keyword = keyword.toLowerCase();
        Page<Certification> certification = certificationRepository.searchByCetificationAndEmployeeId(keyword, pageable, employeeId);

        Page<CertificationDto> certificationDto = certification.map(certi ->
                this.modelMapper.map(certi, CertificationDto.class));

            return certificationDto;
        
    }
}
