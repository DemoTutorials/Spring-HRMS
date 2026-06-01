package com.avisys.empmgmt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.avisys.empmgmt.entity.EducationalQualification;
import com.avisys.empmgmt.entity.Employee;

@Repository
public interface EducationalQualificationRepo extends JpaRepository<EducationalQualification, Long> {

	List<EducationalQualification> findByEmployeeAndIsDeletedFalse(Employee employee);

	Optional<EducationalQualification> findByIdAndIsDeletedFalse(Long id);

	List<EducationalQualification> findByEmployee(Employee employee);

	@Query("SELECT e FROM EducationalQualification e WHERE LOWER(e.educationalQualification) LIKE %:keyword% AND e.employee.id = :employeeId AND e.isDeleted = false")
	Page<EducationalQualification> searchByEducationalQualificationAndEmployeeId(@Param("keyword") String keyword, Pageable pageable, @Param("employeeId")
			Long employeeId);
	
}
