package com.avisys.empmgmt.repository;

 

import java.util.List;
import java.util.Optional;

 

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.avisys.empmgmt.entity.Role;

 

import jakarta.transaction.Transactional;

 

@Repository
@Transactional
public interface RoleRepo extends JpaRepository<Role, Long> {

    public Role findByRoleIdIgnoreCase(String id);

    public Optional<Role> findByRoleId(String id);



    public Role findByRoleIdAndIsDeletedFalse(String id);

 

    public Role findByRoleIdAndIsDeletedTrue(String id);

    public Role findByRoleName(String name);

    public List<Role> findByIsDeletedFalse();

    @Query(" SELECT r from Role r WHERE ( r.isDeleted=false) "
            + "AND         (       lower(r.roleId) LIKE  %:key% "
                        + "OR lower(r.roleName) LIKE  %:key% )"

 

)
    public Optional<Page<Role>> searchRole(@Param("key") String key, Pageable pageable);

 

    public Optional<Role> findByIdAndIsDeletedFalse(long id);






}