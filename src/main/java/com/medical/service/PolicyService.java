/**
 * 
 */
package com.medical.service;


import com.medical.dto.PolicyRequestDto;
import com.medical.dto.PolicyResponseDto;

/**
 * @author Akash
 *
 */
public interface PolicyService {
	
	
	PolicyResponseDto claimService(PolicyRequestDto policyRequestDto);

}
