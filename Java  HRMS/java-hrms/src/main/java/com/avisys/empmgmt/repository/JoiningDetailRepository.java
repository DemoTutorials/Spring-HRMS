package com.avisys.empmgmt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avisys.empmgmt.entity.Employee;
import com.avisys.empmgmt.entity.JoiningDetail;

@Repository
public interface JoiningDetailRepository extends JpaRepository<JoiningDetail,Long>{

	Optional<JoiningDetail> findByEmployeeAndIsDeletedFalse(Employee employee);

	Optional<JoiningDetail> findByIdAndIsDeletedFalse(Long id);

	Optional<JoiningDetail> findByEmployee(Employee employee);

}
