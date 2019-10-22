/**
 * 
 */
package com.medical.controller;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.medical.dto.PolicyRequestDto;
import com.medical.dto.PolicyResponseDto;
import com.medical.service.PolicyService;

/**
 * @author akash
 *
 */

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "*", "*/" }, allowedHeaders = { "*", "*/" })
public class PolicyController {

	public static final Logger lOGGER = LoggerFactory.getLogger(PolicyController.class);

	@Autowired
	private PolicyService policyService;

	/**
	 * 
	 * This method is used for policy
	 * 
	 * @param PolicyRequestDto
	 * @return PolicyResponseDto which returns userId,message, statusCode
	 */

	@PostMapping("/policies")
	public ResponseEntity<PolicyResponseDto> policy(@RequestBody PolicyRequestDto policyRequestDto) {
		lOGGER.info("inside policy controller");
		return new ResponseEntity<>(policyService.claimService(policyRequestDto), HttpStatus.ACCEPTED);

	}

}
