/**
 * 
 */
package com.medical.service;

import org.springframework.stereotype.Service;

import com.medical.dto.ClaimDTO;
import com.medical.dto.ResponseDto;

/**
 * @author shiva
 *
 */
@Service
public interface AddClaimService {
	
	public ResponseDto addClaim(ClaimDTO claimDTO);

}
