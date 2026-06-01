package com.avisys.empmgmt.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.avisys.empmgmt.dto.CreateJoiningDetailDTO;
import com.avisys.empmgmt.dto.JoiningDetailDTO;
import com.avisys.empmgmt.entity.Employee;
import com.avisys.empmgmt.entity.JoiningDetail;
import com.avisys.empmgmt.exception.EmployeeException;
import com.avisys.empmgmt.exception.JoiningDetailAlreadyPresent;
import com.avisys.empmgmt.exception.JoiningDetailNotFound;
import com.avisys.empmgmt.repository.EmployeeRepo;
import com.avisys.empmgmt.repository.JoiningDetailRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class JoiningDetailService {
	
	@Autowired
	private JoiningDetailRepository joiningDetailRepo;
	
	@Autowired
	private EmployeeRepo employeeRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	public JoiningDetailDTO createJoiningDetail(CreateJoiningDetailDTO joiningDetailDto,Long employeeId) {
		Employee employee=employeeRepository.findByIdAndIsDeletedFalse(employeeId).orElseThrow(()->new EmployeeException("Employee Not Found"));
		if(joiningDetailRepo.findByEmployee(employee).isPresent()) {
			throw new JoiningDetailAlreadyPresent("Joining detail already present");
		}
		else {
		   if(joiningDetailDto.getResignationDate()!= null) {
			joiningDetailDto.setRelievingDate(joiningDetailDto.getResignationDate().plusDays(joiningDetailDto.getNoticePeriod()));
			}	
			JoiningDetail joiningDetail = this.modelMapper.map(joiningDetailDto,JoiningDetail.class);
			joiningDetail.setCreatedAt(LocalDateTime.now());
			joiningDetail.setUpdatedAt(null);
			joiningDetail.setUpdatedBy(null);
			joiningDetail.setDeleted(false);
			joiningDetail.setEmployee(employee);			
			JoiningDetail joiningDetailObject=joiningDetailRepo.save(joiningDetail);
			return this.modelMapper.map(joiningDetailObject,JoiningDetailDTO.class);
		}
	}

	public String deleteJoiningDetailByEmployeeId(Long employeeId,String updatedBy) {
		Employee employee=employeeRepository.findByIdAndIsDeletedFalse(employeeId).orElseThrow(()->new EmployeeException("Employee Not Found"));
		JoiningDetail joiningDetailToDelete = joiningDetailRepo
				.findByIdAndIsDeletedFalse(employee.getJoiningDetail().getId())
				.orElseThrow(() -> new JoiningDetailNotFound("Joining Detail Not found to delete for Employee Id " + employeeId));
		joiningDetailToDelete.setDeleted(true);
		joiningDetailToDelete.setUpdatedAt(LocalDateTime.now());
		joiningDetailToDelete.setUpdatedBy(updatedBy);
		joiningDetailRepo.save(joiningDetailToDelete);
		return "Joining Detail Deleted";
	}

	public JoiningDetailDTO updateJoiningDetail(@Valid JoiningDetailDTO joiningDetailDto, Long employeeId) {
		Employee employee=employeeRepository.findByIdAndIsDeletedFalse(employeeId).orElseThrow(()->new EmployeeException("Employee Not Found"));
		JoiningDetail joiningDetailToUpdate = joiningDetailRepo.findByIdAndIsDeletedFalse(joiningDetailDto.getId())
				.orElseThrow(() -> new JoiningDetailNotFound("Joining Detail Not found to update"));
	     	joiningDetailDto.setUpdatedAt(LocalDateTime.now());
	     	joiningDetailDto.setCreatedBy(joiningDetailToUpdate.getCreatedBy());
	     	joiningDetailDto.setCreatedAt(joiningDetailToUpdate.getCreatedAt());
			if(joiningDetailDto.getResignationDate()!= null) {
			       joiningDetailDto.setRelievingDate(joiningDetailDto.getResignationDate().plusDays(joiningDetailDto.getNoticePeriod()));
		     }	
	     	JoiningDetail joiningDetail = this.modelMapper.map(joiningDetailDto,JoiningDetail.class);
			joiningDetail.setEmployee(employee);
			joiningDetail.setDeleted(false);
			JoiningDetail joiningDetailObject=joiningDetailRepo.save(joiningDetail);

		return this.modelMapper.map(joiningDetailObject,JoiningDetailDTO.class);
	}

	public JoiningDetailDTO getJoiningDetailByEmployeeId(Long employeeId) {
		Employee employee=employeeRepository.findByIdAndIsDeletedFalse(employeeId).orElseThrow(()->new EmployeeException("Employee Not Found"));
		JoiningDetail joiningDetail = joiningDetailRepo.findByEmployeeAndIsDeletedFalse(employee)
				.orElseThrow(() -> new JoiningDetailNotFound("Joining Detail Not found for Employee Id "+employeeId));
		JoiningDetailDTO joiningDetailDto = this.modelMapper.map(joiningDetail, JoiningDetailDTO.class);
		return joiningDetailDto;
	}
	
	

}
