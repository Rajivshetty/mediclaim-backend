/**
 * 
 */
package com.medical.controller;

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
	
	@Autowired
	private PolicyService policyService;
	
	@PostMapping("/policies")
	public ResponseEntity<PolicyResponseDto> policy(@RequestBody PolicyRequestDto policyRequestDto){
		
		
		return new ResponseEntity<>(policyService.claimService(policyRequestDto),HttpStatus.ACCEPTED);
		
	}

}
