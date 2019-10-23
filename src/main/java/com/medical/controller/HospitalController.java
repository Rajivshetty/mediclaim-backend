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

import com.medical.dto.HospitalResponseDto;
import com.medical.service.HospitalService;

/**
 * @author Abhishek C
 * @apiNote controller used to fetch Hospitals
 * 
 */
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/api")
public class HospitalController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HospitalController.class);

	@Autowired
	HospitalService hospitalService;

	/**
	 * @apiNote controller to fetch list of hospitals
	 * @return list of HospitalResponseDto
	 */
	@GetMapping("/hospitals")
	public ResponseEntity<List<HospitalResponseDto>> getHospitals() {
		LOGGER.debug("HospitalController class getHospitals method");
		List<HospitalResponseDto> list = hospitalService.getHospitalList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
