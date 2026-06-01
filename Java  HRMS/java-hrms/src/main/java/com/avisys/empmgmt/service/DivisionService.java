package com.avisys.empmgmt.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.avisys.empmgmt.dto.CreateDivisionDto;
import com.avisys.empmgmt.dto.DivisionDto;
import com.avisys.empmgmt.entity.Department;
import com.avisys.empmgmt.entity.Division;
import com.avisys.empmgmt.exception.DepartmentException;
import com.avisys.empmgmt.exception.DesignationNotFound;
import com.avisys.empmgmt.exception.DivisionNotFound;
import com.avisys.empmgmt.repository.DivisonRepository;
import com.avisys.empmgmt.util.Utils;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DivisionService implements IDivisionService {

	@Autowired
	private DivisonRepository divisonRepository;

	@Autowired
	private Utils util;

	@Override
	public List<DivisionDto> getAllDivision() {
		List<Division> divisions = divisonRepository.findByIsDeletedFalse().get();
			return util.getAllDivisionsDto(divisions);

	}

	@Override
	public DivisionDto saveDivision(CreateDivisionDto divisionDto) {
		Optional<Division> optionalDivision = divisonRepository.findByDivisionId(divisionDto.getDivisionId());
		if (optionalDivision.isPresent()) {
			if(optionalDivision.get().isDeleted()==true) {
			throw new DivisionNotFound("Division ID already present but marked deleted");
			}else throw new DivisionNotFound("Division ID already present");
		}
			Division division = new Division(divisionDto.getDivisionId(), divisionDto.getDivisionName(),
					divisionDto.getDivisionDescription(), divisionDto.getOrgCode(), false, LocalDateTime.now(),null, divisionDto.getCreatedBy(), null);
			Division divisionObject=divisonRepository.save(division);
			return util.getDivisionDto(divisionObject);
	}

	@Override
	public String deleteDivisionById(String divisionId,String updatedBy) {

		Division division = divisonRepository.findByDivisionIdAndIsDeletedFalse(divisionId)
				.orElseThrow(() -> new DivisionNotFound("Division Does Not Exist"));
		division.setDeleted(true);
		division.setUpdatedAt(LocalDateTime.now());
		division.setUpdatedBy(updatedBy);
		divisonRepository.save(division);
		return "Division deleted successfully";
	}

	@Override
	public DivisionDto getDivisionById(String divisionId) {

		Division division = divisonRepository.findByDivisionIdAndIsDeletedFalse(divisionId)
				.orElseThrow(() -> new DivisionNotFound("Division Not Found"));
		DivisionDto divisionDto = util.getDivisionDto(division);
		return divisionDto;
	}

	@Override
	public DivisionDto updateDivision(DivisionDto updatedDivisionDto) {
		Division division = divisonRepository.findById(updatedDivisionDto.getId())
				.orElseThrow(() -> new DivisionNotFound("Division Not Found"));
		if (division.isDeleted() == true) {
			throw new DivisionNotFound("Division Does Not Exist");
		} else {
			Optional<Division> divisionByDivisionId = divisonRepository
					.findByDivisionId(updatedDivisionDto.getDivisionId());
			if (divisionByDivisionId.isEmpty() || updatedDivisionDto.getId().equals(division.getId())
					&& updatedDivisionDto.getDivisionId().equals(division.getDivisionId())) {
				updatedDivisionDto.setUpdatedAt(LocalDateTime.now());
				Division updatedDivision = util.getDivision(updatedDivisionDto);
				Division divisionObject=divisonRepository.save(updatedDivision);
				return util.getDivisionDto(divisionObject);
			} else {
				throw new DivisionNotFound("DivisionId already Exist");
			}
		}
	}

	@Override
	public Page<Division> searchDivision(String searchValue, Pageable pageable) {
		Page<Division> divisions = divisonRepository.searchDivision(searchValue, pageable)
				.orElseThrow(() -> new DivisionNotFound("Division Not Found"));
		
			return divisions;		
	}

}
