package com.avisys.empmgmt.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.avisys.empmgmt.entity.Certification;
import com.avisys.empmgmt.entity.Employee;
import com.avisys.empmgmt.entity.WorkExperience;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, Long>{

	List<Certification> findByEmployeeAndIsDeletedFalse(Employee employee);

	Optional<Certification> findByIdAndIsDeletedFalse(Long certificationId);
	   
	@Query("SELECT c FROM Certification c WHERE LOWER(c.certification) LIKE %:keyword% AND c.employee.id = :employeeId AND c.isDeleted = false")
    Page<Certification> searchByCetificationAndEmployeeId(@Param("keyword") String keyword, Pageable pageable, @Param("employeeId") Long employeeId);

}
