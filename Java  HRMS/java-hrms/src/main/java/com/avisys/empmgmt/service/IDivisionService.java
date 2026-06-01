package com.avisys.empmgmt.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import com.avisys.empmgmt.dto.CreateDivisionDto;
import com.avisys.empmgmt.dto.DivisionDto;
import com.avisys.empmgmt.entity.Division;

public interface IDivisionService {
   List<DivisionDto> getAllDivision();

   DivisionDto saveDivision(CreateDivisionDto division);

   String deleteDivisionById(String divisionId,String updatedBy);

   DivisionDto getDivisionById(String divisionId);

   DivisionDto updateDivision(DivisionDto division);

   Page<Division> searchDivision(String searchValue,Pageable pageable);
   
   
}
