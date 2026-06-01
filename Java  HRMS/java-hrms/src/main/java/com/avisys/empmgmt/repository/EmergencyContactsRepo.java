package com.avisys.empmgmt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.avisys.empmgmt.entity.EmergencyContacts;
import com.avisys.empmgmt.entity.Employee;

	@Repository
	public interface EmergencyContactsRepo extends JpaRepository<EmergencyContacts, Long>  {

		List<EmergencyContacts> findByEmployeeAndIsDeletedFalse(Employee employee);

		Optional<EmergencyContacts> findByIdAndIsDeletedFalse(Long emergencyContactsId);

		Optional<EmergencyContacts> findByEmergencyContactNumberAndIsDeletedFalse(String emergencyContactNumber);

		List<EmergencyContacts> findByEmployee(Employee employee);
	
		@Query("SELECT e FROM EmergencyContacts e WHERE (LOWER(e.emergencyContactName) LIKE %:keyword% OR LOWER(e.relation) LIKE %:keyword%) AND e.employee.id = :employeeId AND e.isDeleted = false")
		Page<EmergencyContacts> searchByEmergencyContactsAndEmployeeId(@Param("keyword") String keyword, Pageable pageable, @Param("employeeId") Long employeeId);
}
