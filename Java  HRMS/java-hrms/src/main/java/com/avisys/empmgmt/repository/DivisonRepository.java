package com.avisys.empmgmt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.avisys.empmgmt.entity.Division;
import com.avisys.empmgmt.entity.Employee;

@Repository
public interface DivisonRepository extends JpaRepository<Division,Long>{
      
	 Optional<Division> findByDivisionId(String divisionId);
	
	 Optional<Division> findByDivisionIdAndIsDeletedFalse(String divisionId);

	 @Query("Select d from Division d where (lower(d.divisionId) like %:searchValue% or lower(d.divisionName) like %:searchValue% or lower(d.divisionDescription) like %:searchValue%)and d.isDeleted = false")
	 Optional<Page<Division>> searchDivision(@Param("searchValue")String searchValue, Pageable pageble);
	 
	 Optional<List<Division>> findByIsDeletedFalse();

	 Optional<Division> findByIdAndIsDeletedFalse(Long id);
}
