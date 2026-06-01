package com.avisys.empmgmt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.avisys.empmgmt.entity.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long>{

	List<Department> findByIsDeletedFalse();

	Optional<Department> findByDepartmentId(String departmentId);
	
	@Query("SELECT d FROM Department d WHERE (LOWER(d.departmentId) LIKE %:key% OR LOWER(d.departmentName) LIKE %:key% OR LOWER(d.departmentDescription) LIKE %:key% ) and d.isDeleted=false")
	Page<Department> searchByDepartment(Pageable pageable, String key);

	Optional<Department> findByIdAndIsDeletedFalse(Long id);
	
}
