package com.avisys.empmgmt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.avisys.empmgmt.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

	@Query("SELECT e FROM Employee e WHERE (LOWER(e.firstName) LIKE %:key% OR LOWER(e.middleName) LIKE %:key% OR LOWER(e.lastName) LIKE %:key% ) and e.isDeleted=false")
	Page<Employee> searchByEmployee(Pageable pageable, String key);

	List<Employee> findByIsDeletedFalse();

	Optional<Employee> findByIdAndIsDeletedFalse(Long employeeId);

	Optional<Employee> findByEmployeeCodeAndIsDeletedFalse(String employeeCode);

	Optional<Employee> findByEmployeeCode(String employeeCode);


}
