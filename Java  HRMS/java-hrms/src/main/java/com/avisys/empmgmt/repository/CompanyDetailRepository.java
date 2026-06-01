package com.avisys.empmgmt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avisys.empmgmt.entity.CompanyDetail;
import com.avisys.empmgmt.entity.Employee;

public interface CompanyDetailRepository extends JpaRepository<CompanyDetail,Long>{

	Optional<CompanyDetail> findByEmployeeAndIsDeletedFalse(Employee employee);

	Optional<CompanyDetail> findByIdAndIsDeletedFalse(Long id);

	Optional<CompanyDetail> findByEmployee(Employee employee);


}
