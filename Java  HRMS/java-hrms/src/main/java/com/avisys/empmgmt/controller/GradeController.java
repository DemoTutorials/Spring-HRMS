package com.avisys.empmgmt.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avisys.empmgmt.dto.GradeDTO;
import com.avisys.empmgmt.exception.GradeException;
import com.avisys.empmgmt.service.GradeService;
import com.avisys.empmgmt.util.ApiResponse;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("grade")
public class GradeController {

	@Autowired
	private GradeService gradeService;

	Logger logger = Logger.getLogger(GradeController.class);

	@PostMapping("/create")
	public ResponseEntity<GradeDTO> createGrade(@Valid @RequestBody GradeDTO gradeDtoObject)
			throws MethodArgumentNotValidException, GradeException {
		logger.info("Create Grade Called");
		return gradeService.saveGrade(gradeDtoObject);
	}

	@GetMapping("/get")
	public ResponseEntity<?> gradePagingRequest(@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword, @PageableDefault(page = 0, size = 2) Pageable pageable
			)
			throws GradeException {
		logger.info("Get All Grade with paging Called");
		return gradeService.getGradesWithPaging(pageable, keyword);
	}

	@GetMapping("/get-all")

	public ResponseEntity<?> getAllGrade() throws GradeException {
		logger.info("Get All Grade Called");
		return gradeService.getAllGrade();
	}

	@GetMapping("/{gradeId}")
	public ResponseEntity<GradeDTO> findByGradeId(@PathVariable("gradeId") String gradeId) throws GradeException {
		logger.info("find By Grade Id Called");
		return gradeService.findGradeId(gradeId);
	}

	@PutMapping("/update")
	public ResponseEntity<GradeDTO> updateGrade(@Valid @RequestBody GradeDTO gradeDtoObject) throws GradeException {
		logger.info("Update Grade Id Called ");
		return gradeService.updateGrade(gradeDtoObject);
	}

	@DeleteMapping("/delete/{gradeId}")
	public ResponseEntity<Object> deleteGrade(@PathVariable("gradeId") String gradeId,@RequestParam(value = "updatedBy") String updatedBy) throws GradeException {
		logger.info("Delete Grade ID Called ");
		return gradeService.deleteGrade(gradeId,updatedBy);
	}
}
