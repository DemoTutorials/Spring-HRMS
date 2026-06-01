package com.avisys.empmgmt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.avisys.empmgmt.entity.Employee;
import com.avisys.empmgmt.entity.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
	Optional<Grade> findByGradeId(String gradeId);

	Optional<Grade> findByGradeIdAndIsDeletedFalse(String gradeId);

	Optional<List<Grade>> findByIsDeletedFalse();

	@Query("SELECT g FROM Grade g WHERE (LOWER(g.gradeId) LIKE %:key% OR LOWER(g.gradeName) LIKE %:key% OR LOWER(g.gradeType) LIKE %:key%) and g.isDeleted=false")
	Page<Grade> getGradeWithPagingAndSearch(Pageable pageable, String key);

	Optional<Grade> findByIdAndIsDeletedFalse(Long id);
}
