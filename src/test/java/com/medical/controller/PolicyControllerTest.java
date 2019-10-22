/**
 * 
 */
package com.medical.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.medical.dto.PolicyRequestDto;
import com.medical.dto.PolicyResponseDto;
import com.medical.entity.User;
import com.medical.service.PolicyServiceImpl;
import com.medical.util.MedicalClaimConstants;

/**
 * @author akash
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PolicyControllerTest {
	
	@Mock
	private PolicyServiceImpl policyServiceImpl;
	@InjectMocks
	private PolicyController policyController;
	
	User user = null;
	PolicyRequestDto policyRequestDto = null;
	PolicyResponseDto policyResponseDto = null;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		user = new User();
		user.setUserId(1);
		user.setAadharNo(102l);
		user.setDob(LocalDate.now());
		user.setPolicyNo(10);

		policyRequestDto = new PolicyRequestDto();
		policyRequestDto.setPolicyNo(101);
		policyRequestDto.setAadharNo(102l);
		policyRequestDto.setDob(LocalDate.now());

		policyResponseDto = new PolicyResponseDto();
		policyResponseDto.setUserId(1);
		policyResponseDto.setMessage(MedicalClaimConstants.CLAIM_MESSAGE);
		policyResponseDto.setStatusCode(MedicalClaimConstants.CLAIM_STATUS_CODE);

	}
	
	@Test
	public void testPolicy() {
		
		Mockito.when(policyServiceImpl.claimService(policyRequestDto)).thenReturn(policyResponseDto);
		PolicyResponseDto actualPolicyResponseDto =policyServiceImpl.claimService(policyRequestDto);
		assertEquals(policyResponseDto.getStatusCode(), actualPolicyResponseDto.getStatusCode());
		
	}

}
