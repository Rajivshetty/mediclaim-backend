/**
 * 
 */
package com.medical.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.dto.PolicyRequestDto;
import com.medical.dto.PolicyResponseDto;
import com.medical.entity.User;
import com.medical.exception.MedicalClaimException;
import com.medical.repository.UserRepository;
import com.medical.util.MedicalClaimConstants;

/**
 * @author akash
 *
 */

@Service
public class PolicyServiceImpl implements PolicyService {

	public static final Logger lOGGER = LoggerFactory.getLogger(PolicyServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	
	/**
	 * This method is intended to user policy with proper credentials
	 *
	 * @param PolicyRequestDto
	 *            is the input object which have policyNo, aadharNo,DOB
	 * @exception USER_NOT_EXIST
	 * @return PolicyResponseDto which returns userId,message, statusCode
	 */

	@Override
	public PolicyResponseDto claimService(PolicyRequestDto policyRequestDto) {

		lOGGER.info("In Policy service ");
		Optional<User> user = userRepository.findByPolicyNo(policyRequestDto.getPolicyNo());
		if (!user.isPresent()) {
			throw new MedicalClaimException(MedicalClaimConstants.USER_NOT_EXIST);
		}
		if (user.get().getAadharNo() != policyRequestDto.getAadharNo()) {

			throw new MedicalClaimException(MedicalClaimConstants.INVALID_AADHAR_NUMBER);
		}

		if (!user.get().getDob().isEqual(policyRequestDto.getDob())) {
			throw new MedicalClaimException(MedicalClaimConstants.INVALID_DOB_NUMBER);
		} 

		lOGGER.debug("after validation in policy service");
		PolicyResponseDto policyResponseDto = new PolicyResponseDto();
		policyResponseDto.setUserId(user.get().getUserId());
		policyResponseDto.setMessage(MedicalClaimConstants.CLAIM_MESSAGE);
		policyResponseDto.setStatusCode(MedicalClaimConstants.CLAIM_STATUS_CODE);
		return policyResponseDto;
	}
	

}
