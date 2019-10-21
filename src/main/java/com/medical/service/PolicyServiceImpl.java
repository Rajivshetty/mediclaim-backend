/**
 * 
 */
package com.medical.service;

import java.util.Optional;

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

	@Autowired
	private UserRepository userRepository;

	@Override
	public PolicyResponseDto claimService(PolicyRequestDto policyRequestDto) {

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

		PolicyResponseDto policyResponseDto = new PolicyResponseDto();
		policyResponseDto.setUserId(user.get().getUserId());
		policyResponseDto.setMessage(MedicalClaimConstants.CLAIM_MESSAGE);
		policyResponseDto.setStatusCode(MedicalClaimConstants.CLAIM_STATUS_CODE);
		return policyResponseDto;
	}

}
