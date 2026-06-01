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
import com.avisys.empmgmt.entity.EmployeeType;

 

@Repository
public interface EmployeeTypeRepository extends JpaRepository<EmployeeType, Long> {

 

    Optional<EmployeeType> findByIdAndIsDeleted(Long id, boolean isDeleted);

 

    Page<EmployeeType> findAllByIsDeleted(Pageable pageable, boolean isDeleted);

 

 

    Page<EmployeeType> findByTypeIsContainingAndIsDeleted(String type, Pageable pageable, boolean isDeleted);
    @Query(" SELECT e from EmployeeType e WHERE ( e.isDeleted=false) "
            + "AND         (       lower(e.employeeTypeId) LIKE  %:key% "
                        + "OR lower(e.type) LIKE  %:key%) ") 
    Optional<Page<EmployeeType>> searchEmployeeType(@Param("key")String key, Pageable pageble);

 

    List<EmployeeType> findAllByIsDeleted(boolean isDeleted);

 

 

    Optional<EmployeeType> findByEmployeeTypeId(String employeeTypeId);

 

 

    Optional<EmployeeType> findByEmployeeTypeIdAndIsDeleted(String employeeTypeId, boolean b);

 

 

    public EmployeeType findByEmployeeTypeIdIgnoreCase(String employeeTypeId);



	Optional<EmployeeType> findByIdAndIsDeletedFalse(Long id);
}
