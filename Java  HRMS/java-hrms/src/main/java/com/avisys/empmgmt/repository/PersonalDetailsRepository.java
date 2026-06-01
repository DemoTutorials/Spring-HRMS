package com.avisys.empmgmt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avisys.empmgmt.entity.Employee;
import com.avisys.empmgmt.entity.PersonalDetails;

public interface PersonalDetailsRepository extends JpaRepository<PersonalDetails, Long> {

	 Optional<List<PersonalDetails>> findByIsDeletedFalse();

	 Optional<PersonalDetails> findByIdAndIsDeletedFalse(Long id);

	 Optional<PersonalDetails> findByEmployeeAndIsDeletedFalse(Employee employee);

	Optional<PersonalDetails> findByAadhaarNumberOrPanCardNumberOrPassportNumberAndIsDeletedFalse(String aadhaarNumber,
			String panCardNumber, String passportNumber);

	Optional<PersonalDetails> findByEmployee(Employee employee);

	Optional<PersonalDetails> findByAadhaarNumberOrPanCardNumberOrPassportNumber(String aadhaarNumber,
			String panCardNumber, String passportNumber);

}
