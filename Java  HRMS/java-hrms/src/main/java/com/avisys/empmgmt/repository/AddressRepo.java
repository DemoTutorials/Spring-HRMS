package com.avisys.empmgmt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.avisys.empmgmt.entity.Address;
import com.avisys.empmgmt.entity.Employee;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long>{


	Optional<Address> findByEmployeeId(Long employeeId);

	Optional<Address> findByIdAndIsDeletedFalse(Long addressId);

	List<Address> findByEmployeeAndIsDeletedFalse(Employee employee);

	List<Address> findByEmployee(Employee employee);

	@Query("SELECT a FROM Address a WHERE a.employee.id = :employeeId AND a.addressType = :addressType AND a.isDeleted = false")
	Address findByEmployeeAndAddressTypeAndIsDeletedFalse(@Param("employeeId") Long employeeId, @Param("addressType") String addressType);
}
