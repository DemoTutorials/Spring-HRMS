package com.avisys.empmgmt.service;

 

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.avisys.empmgmt.dto.CreateDTO;
import com.avisys.empmgmt.dto.GETResponse;
import com.avisys.empmgmt.dto.UpdateDTO;
import com.avisys.empmgmt.entity.Role;
import com.avisys.empmgmt.exception.DesignationNotFound;
import com.avisys.empmgmt.exception.NoRoleFoundException;
import com.avisys.empmgmt.exception.RoleAlreadyPresentException;
import com.avisys.empmgmt.repository.RoleRepo;
import com.avisys.empmgmt.util.Utils;

 

import jakarta.transaction.Transactional;

 

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

 

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private Utils util;

    @Override
    public List<GETResponse> getRoles()
    {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        List<Role> allRoles=  this.roleRepo.findByIsDeletedFalse();        
         return  allRoles.stream().map(role->util.roleToDTO(role)).collect(Collectors.toList());
    }


    @Override
    public GETResponse createRole(CreateDTO role)
    {
        LocalDateTime now = LocalDateTime.now();
        if(roleRepo.findByRoleId(role.getRoleId()).isPresent()) {
            Role deletedRole=this.roleRepo.findByRoleIdAndIsDeletedTrue(role.getRoleId().toUpperCase());
            if(deletedRole!=null)
                {throw new DesignationNotFound("Role ID already present but marked deleted");}
            throw new DesignationNotFound("Role ID already present");
        }else {

        Role roleToCreate=new Role(role.getRoleId().toUpperCase(),role.getRoleName(),role.getOrgCode().toUpperCase(),false,now,now,role.getCreatedBy(),null);
        Role roleCreated= roleRepo.save(roleToCreate);    
        return util.roleToDTO(roleCreated);
            }
//        
    }

    @Override
    public Role findById(String id) {
        Role foundedRole=this.roleRepo.findByRoleIdAndIsDeletedFalse(id.toUpperCase());
        if(foundedRole!=null) return foundedRole;
        throw new NoRoleFoundException();
    }

    @Override
    public boolean deleteRole(String id,String updatedBy) 
    {
        Role roleToBeDeleted=this.roleRepo.findByRoleIdAndIsDeletedFalse(id.toUpperCase());
        if(roleToBeDeleted==null) throw new NoRoleFoundException();
        roleToBeDeleted.setDeleted(true);
        roleToBeDeleted.setUpdatedAt(LocalDateTime.now());
        roleToBeDeleted.setUpdatedBy(updatedBy);
        this.roleRepo.save(roleToBeDeleted);
        return true;

    }

    @Override
    public GETResponse updateRole(UpdateDTO updateDto) {

        Role roleToBeUpdated=this.roleRepo.findById(updateDto.getId()).orElseThrow(()->new NoRoleFoundException());
        Role isRoleIdAlreadyPresent=roleRepo.findByRoleIdIgnoreCase(updateDto.getRoleId());
        if(isRoleIdAlreadyPresent==null || (isRoleIdAlreadyPresent.getRoleId().equals(updateDto.getRoleId()) && isRoleIdAlreadyPresent.getId()==updateDto.getId()) )
        {
            roleToBeUpdated.setRoleId(updateDto.getRoleId().toUpperCase());
            roleToBeUpdated.setRoleName(updateDto.getRoleName());
            roleToBeUpdated.setOrgCode(updateDto.getOrgCode().toUpperCase());
            LocalDateTime now = LocalDateTime.now();
            roleToBeUpdated.setUpdatedAt(now);
            roleToBeUpdated.setUpdatedBy(updateDto.getUpdatedBy());
            Role roleUpdated= this.roleRepo.save(roleToBeUpdated); 
            return this.util.roleToDTO(roleUpdated);

        }
        else
        {
            throw new RoleAlreadyPresentException();
        }

    }

 

    @Override
    public Optional<Page<Role>> searchingSortingPagination(String key,Pageable  pageable) {
        Optional<Page<Role>>foundedPages= this.roleRepo.searchRole(key.toLowerCase(),pageable);
        return foundedPages;
    }
 

}