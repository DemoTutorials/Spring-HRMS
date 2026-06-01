package com.avisys.empmgmt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.avisys.empmgmt.entity.CommonMaster;

import jakarta.transaction.Transactional;

@Transactional
public interface CommonMastersRepo extends JpaRepository<CommonMaster, Long>{

	Optional<CommonMaster> findByMasterNameAndIsMasterTrueAndIsDeletedFalse(String masterName);

	List<CommonMaster> findListByMasterNameAndIsMasterFalseAndIsDeletedFalse(Pageable pageable, String masterName);

	@Query("SELECT c FROM CommonMaster c WHERE (LOWER(c.masterName) LIKE %:key% OR LOWER(c.code) LIKE %:key% OR LOWER(c.value) LIKE %:key% ) and c.isMaster=true and c.isDeleted=false")
	Page<CommonMaster> searchByParentCommonMaster(Pageable pageable, String key);

	CommonMaster findByIdAndIsDeletedFalse(Long id);

	@Query("SELECT c FROM CommonMaster c WHERE c.masterName = :masterName and c.foreignKey = :dependent and c.isMaster=false and c.isDeleted=false")
	List<CommonMaster> findByDependentMaster(Pageable pageable, @Param("masterName") String masterName, @Param("dependent") String dependent);

	@Query("SELECT c FROM CommonMaster c WHERE c.masterName = :masterName and (LOWER(c.masterName) LIKE %:key% OR LOWER(c.code) LIKE %:key% OR LOWER(c.value) LIKE %:key% ) and c.isMaster=false and c.isDeleted=false" +
	        " ORDER BY c.priority ASC, c.code ASC")
	Page<CommonMaster> searchByChildCommonMaster(String key, Pageable pageable, String masterName);

	Optional<CommonMaster> findByCode(String code);

}
