package com.avisys.empmgmt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.avisys.empmgmt.entity.Designation;

@Repository
public interface DesignationRepo extends JpaRepository<Designation, Long> {

	Optional<Designation> findByDesignationIdAndIsDeletedFalse(String designationId);
	Optional<Designation> findByDesignationIdAndIsDeletedTrue(String designationId);
	Optional<Designation> findByDesignationId(String designationId);
	Optional<List<Designation>> findByIsDeletedFalse();
	
	@Query("select d from Designation d where (lower(d.designationId) like %:designationKey% or lower(d.designationName) like %:designationKey% or lower(d.designationDesc) like %:designationKey%) and d.isDeleted=FALSE")
	Optional<Page<Designation>> searchDesignation(@Param("designationKey") String designationKey,Pageable pageable);
	
	Optional<Designation> findByIdAndIsDeletedFalse(Long id);
}
