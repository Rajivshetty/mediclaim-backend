package com.medical.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medical.dto.DiseaseResponseDto;
import com.medical.service.DiseaseService;

/**
 * @author Abhishek C
 * @apiNote controller used to fetch diseases
 * 
 */
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/api")
public class DiseaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DiseaseController.class);

	@Autowired
	DiseaseService diseaseService;

	/**
	 * 
	 * @apiNote controller to fetch list of diseases
	 * @return list of hospitals
	 */
	@GetMapping("/diseases")
	public ResponseEntity<List<DiseaseResponseDto>> getDiseaseList() {
		LOGGER.debug("DiseaseController class getDiseaseList method");
		List<DiseaseResponseDto> diseasesList = diseaseService.getDiseaseList();
		return new ResponseEntity<>(diseasesList, HttpStatus.OK);

	}

}
