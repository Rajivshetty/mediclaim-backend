/**
 * 
 */
package com.medical.service;

import com.medical.dto.PolicyRequestDto;
import com.medical.dto.PolicyResponseDto;

/**
 * @author User1
 *
 */
public interface PolicyService {

	/**
	 * @param policyRequestDto
	 * @return PolicyRespnseDto
	 */
	PolicyResponseDto claimService(PolicyRequestDto policyRequestDto);

}
