package com.avisys.empmgmt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.avisys.empmgmt.entity.Designation;
import com.avisys.empmgmt.entity.Organization;
import com.avisys.empmgmt.entity.Role;

public interface OrganizationRepo extends JpaRepository<Organization, Long> {
	
	public Organization findByOrganizationCodeIgnoreCase(String orgCode);


	
	Optional<Organization> findByOrganizationCodeAndIsDeletedFalse(String orgCode);
	public Organization findByOrganizationCodeAndIsDeletedTrue(String orgCode);
	
	Optional<Organization> findByOrganizationCode(String orgCode);
	Optional<List<Organization>> findByIsDeletedFalse();
	
	@Query(" SELECT o from Organization o WHERE ( o.isDeleted=false) "
			+ "AND		 ( 	  lower(o.organizationCode) LIKE  %:key% "
						+ "OR lower(o.organizationName) LIKE  %:key% "
			+"OR lower(o.organizationDesc) LIKE %:key%)"

)
	public Optional<Page<Organization>> searchOrganization(@Param("key") String key, Pageable pageable);
}
