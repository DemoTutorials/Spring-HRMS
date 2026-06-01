package com.avisys.empmgmt.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.avisys.empmgmt.entity.Employee;
import com.avisys.empmgmt.entity.WorkExperience;

@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Long>{

	List<WorkExperience> findByEmployeeAndIsDeletedFalse(Employee employee);

	Optional<WorkExperience> findByIdAndIsDeletedFalse(Long workExperienceId);
	   
	@Query("SELECT e FROM WorkExperience e WHERE LOWER(e.companyName) LIKE %:keyword% AND e.employee.id = :employeeId AND e.isDeleted = false")
    Page<WorkExperience> searchByCompanyNameAndEmployeeId(@Param("keyword") String keyword, Pageable pageable, @Param("employeeId") Long employeeId);

}
