package com.avisys.empmgmt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.avisys.empmgmt.entity.Employee;
import com.avisys.empmgmt.entity.Visa;

@Repository
public interface VisaRepository extends JpaRepository<Visa, Long>{

	Optional<Visa> findByVisaNumberAndIsDeletedFalse(String visaNumber);

	List<Visa> findByEmployeeAndIsDeletedFalse(Employee employee);

	Optional<Visa> findByIdAndIsDeletedFalse(Long id);

	@Query("SELECT v FROM Visa v WHERE LOWER(v.countryCode) LIKE %:keyword% AND v.employee.id = :employeeId AND v.isDeleted = false")
	Page<Visa> searchByVisaAndEmployeeId(@Param("keyword") String keyword, Pageable pageable, @Param("employeeId") Long employeeId);

	Optional<Visa> findByVisaNumber(String visaNumber);

		
}
