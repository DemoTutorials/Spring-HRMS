package com.avisys.empmgmt.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.avisys.empmgmt.dto.GradeDTO;
import com.avisys.empmgmt.entity.Grade;
import com.avisys.empmgmt.exception.DepartmentException;
import com.avisys.empmgmt.exception.GradeException;
import com.avisys.empmgmt.repository.GradeRepository;
import com.avisys.empmgmt.util.ApiResponse;

import jakarta.validation.Valid;

@Service
public class GradeService {

	@Autowired
	private GradeRepository gradeRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<GradeDTO> saveGrade(GradeDTO gradeDtoObject) throws GradeException {
		Optional<Grade> objectPresent = gradeRepository.findByGradeId(gradeDtoObject.getGradeId());
		if (objectPresent.isPresent()) {
			if(objectPresent.get().isDeleted()==true) {
				String message = "Grade ID already present but marked deleted";
				throw new GradeException(message);
				}
			else {
			String message = "Grade ID already present";
			throw new GradeException(message);
			}
		} else {
			@Valid
			Grade gradeObject = modelMapper.map(gradeDtoObject, Grade.class);
			gradeObject.setCreatedAt(LocalDateTime.now());
			gradeObject.setDeleted(false);
			gradeObject.setUpdatedBy(null);
			gradeObject.setCreatedBy(gradeDtoObject.getCreatedBy());
			Grade grade=gradeRepository.save(gradeObject);
			return new ResponseEntity<GradeDTO>(this.modelMapper.map(grade, GradeDTO.class), HttpStatus.OK);
		}
	}

	public ResponseEntity<List<Grade>> getAllGrade() throws GradeException {
		Optional<List<Grade>> allGrade = gradeRepository.findByIsDeletedFalse();
		return new ResponseEntity<>(allGrade.get(), HttpStatus.OK);
	}

	public ResponseEntity<?> getGradesWithPaging(Pageable pageable, String keyword) throws GradeException {
		keyword = keyword.toLowerCase();
		Page<Grade> page = gradeRepository.getGradeWithPagingAndSearch(pageable, keyword);
		
		return new ResponseEntity<>(page, HttpStatus.OK);
	}

	public ResponseEntity<GradeDTO> findGradeId(String gradeId) throws GradeException {
		Optional<Grade> gradeObject = gradeRepository.findByGradeIdAndIsDeletedFalse(gradeId);
		if (gradeObject.isPresent()) {
			GradeDTO gradeDtoObject = new GradeDTO(gradeObject.get());
			return new ResponseEntity<>(gradeDtoObject, HttpStatus.OK);
		} else {
			throw new GradeException("Grade Not Present");
		}
	}

	public ResponseEntity<Object> deleteGrade(String gradeId,String updatedBy) throws GradeException {
		Optional<Grade> gradeOptinal = gradeRepository.findByGradeIdAndIsDeletedFalse(gradeId);
		if (gradeOptinal.isPresent()) {
			Grade grade = gradeOptinal.get();
			grade.setDeleted(true);
			grade.setUpdatedAt(LocalDateTime.now());
			grade.setUpdatedBy(updatedBy);
			gradeRepository.save(grade);
			return new ResponseEntity<>(new ApiResponse("Grade Deleted successfully", LocalDateTime.now()),
					HttpStatus.OK);
		} else {
			String message = "Grade Not Present inn the Database";
			throw new GradeException(message);
		}

	}

	public ResponseEntity<GradeDTO> updateGrade(GradeDTO gradeDTO) throws GradeException {
		if (gradeDTO.getId() == null) {
			throw new GradeException("Id Should Not be null");
		}
		Optional<Grade> gradeOptionalObj = gradeRepository.findById(gradeDTO.getId());
		if (gradeOptionalObj.isEmpty() || gradeOptionalObj.get().isDeleted() == true) {
			String message = "No Grade Present in the Database";
			throw new GradeException(message);
		}
		Grade gradeObj = gradeOptionalObj.get();

		Optional<Grade> gradeIdObj = gradeRepository.findByGradeId(gradeDTO.getGradeId());
		if (gradeIdObj.isEmpty() || gradeDTO.getGradeId().equals(gradeObj.getGradeId())) {
			Grade grade = gradeOptionalObj.get();
			grade.setGradeId(gradeDTO.getGradeId());
			grade.setGradeName(gradeDTO.getGradeName());
			grade.setGradeType(gradeDTO.getGradeType());
			grade.setOrgCode(gradeDTO.getOrgCode());
			grade.setUpdatedBy(gradeDTO.getUpdatedBy());
			grade.setUpdatedAt(LocalDateTime.now());
			Grade gradeObject= gradeRepository.save(grade);
			return new ResponseEntity<GradeDTO>(this.modelMapper.map(gradeObject, GradeDTO.class), HttpStatus.OK);
		} 
		else throw new GradeException("Invalid GradeId ");
	}
}