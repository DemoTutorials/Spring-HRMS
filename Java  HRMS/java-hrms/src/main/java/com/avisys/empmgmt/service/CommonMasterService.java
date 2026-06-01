package com.avisys.empmgmt.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.avisys.empmgmt.dto.CommonMasterDto;
import com.avisys.empmgmt.dto.DepartmentDto;
import com.avisys.empmgmt.entity.CommonMaster;
import com.avisys.empmgmt.entity.Department;
import com.avisys.empmgmt.exception.CommonMasterException;
import com.avisys.empmgmt.exception.DepartmentException;
import com.avisys.empmgmt.repository.CommonMastersRepo;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class CommonMasterService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CommonMastersRepo masterRepository;
	
	public CommonMasterDto createCommonMaster(@Valid CommonMasterDto masterDto) {
		Optional<CommonMaster> masters = masterRepository.findByCode(masterDto.getCode());
		if (masters.isPresent()) {
			if(masters.get().isDeleted()==true) {
			throw new CommonMasterException("Common master already present but marked deleted");
			}else throw new CommonMasterException("Common master already present");
		}
		CommonMaster commonMasters=this.modelMapper.map(masterDto, CommonMaster.class);
		commonMasters.setCreatedAt(LocalDateTime.now());
		commonMasters.setCreatedBy(masterDto.getCreatedBy());
		commonMasters.setUpdatedBy(null);
		commonMasters.setDeleted(false);
		
		CommonMaster commonMaster=masterRepository.save(commonMasters);		
		return this.modelMapper.map(commonMaster, CommonMasterDto.class);
			
	}
	
	public Page<CommonMasterDto> getByMasterName( String keyword, Pageable pageable, String masterName) {
		CommonMaster masters = masterRepository.findByMasterNameAndIsMasterTrueAndIsDeletedFalse(masterName).orElseThrow(()->new CommonMasterException("Master not found"));
		keyword = keyword.toLowerCase();
		Page<CommonMaster> commonMasters=this.masterRepository.searchByChildCommonMaster(keyword,pageable, masterName);
		
		Page<CommonMasterDto> commonMasterDto= (Page<CommonMasterDto>) commonMasters.map((master)-> this.modelMapper.map(master,CommonMasterDto.class));
		return commonMasterDto;
	}
	
	public String deleteCommonMaster(Long id, String updatedBy) {
		CommonMaster commonMaster=this.masterRepository.findByIdAndIsDeletedFalse(id);
		if(commonMaster!=null) {
		commonMaster.setDeleted(true);
		commonMaster.setUpdatedAt(LocalDateTime.now());
		commonMaster.setUpdatedBy(updatedBy);
		masterRepository.save(commonMaster);
		
		return "Common master deleted successfully";
	}
		else throw new CommonMasterException("common master code does not exist");
	
	}
	
	public CommonMasterDto updateCommonMaster( CommonMasterDto commonMasterDto) {
		CommonMaster commonMaster=masterRepository.findByIdAndIsDeletedFalse(commonMasterDto.getId());
   
		if(commonMaster!=null) { 
	       CommonMaster master= modelMapper.map(commonMasterDto, CommonMaster.class);
      
	       master.setUpdatedAt(LocalDateTime.now());
	       master.setUpdatedBy(commonMasterDto.getUpdatedBy());
	       master.setCreatedBy(commonMaster.getCreatedBy());
	       master.setCreatedAt(commonMaster.getCreatedAt());
	       master.setDeleted(false);

	        CommonMaster commonMasters = masterRepository.save(master);
	 
	    return  this.modelMapper.map(commonMasters, CommonMasterDto.class);
	    } else throw new CommonMasterException("Common Master Id doesn't exist for update");
	}
	
	public Page<CommonMasterDto> searchParentCommonMaster( String keyword,Pageable pageable){
			keyword = keyword.toLowerCase();
		Page<CommonMaster> commonMaster = masterRepository.searchByParentCommonMaster(pageable,keyword);
		Page<CommonMasterDto> commonMasterDto = (Page<CommonMasterDto>) commonMaster .map((master) -> this.modelMapper.map(master, CommonMasterDto.class));
		
			return commonMasterDto;
	}

	public List<CommonMasterDto> getByMaster(Pageable pageable, String masterName, String dependent) {
	    CommonMaster masters = masterRepository.findByMasterNameAndIsMasterTrueAndIsDeletedFalse(masterName).orElseThrow(() -> new CommonMasterException("Master not found"));

	    List<CommonMaster> commonMasters;
	    
	    if (dependent != null) {
	        commonMasters = this.masterRepository.findByDependentMaster(pageable, masterName, dependent);
	    } else {
	        // Handle the case where dependent is not provided
	        commonMasters = this.masterRepository.findListByMasterNameAndIsMasterFalseAndIsDeletedFalse(pageable, masterName);
	    }

	    List<CommonMasterDto> commonMasterDto = commonMasters.stream()
	            .map(master -> this.modelMapper.map(master, CommonMasterDto.class))
	            .collect(Collectors.toList());

	    return commonMasterDto;
	}

	public CommonMasterDto getByCommonMasterId(Long commonMasterId) {
		Optional<CommonMaster> master = masterRepository.findById(commonMasterId);
		if (master.isPresent() && !master.get().isDeleted()) {
			return this.modelMapper.map(master, CommonMasterDto.class);
		} else {
			throw new CommonMasterException("Common Masters not found");
		}
	}
	
}
