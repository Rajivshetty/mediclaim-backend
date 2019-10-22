package com.medical.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.medical.dto.ApproveReqDto;
import com.medical.dto.ApproveResDto;
import com.medical.dto.ClaimResDto;

/**
 * 
 * @author mahesh
 *
 */
@Service
public interface ApproveService {

	List<ClaimResDto> claimList(@NotNull Integer approverId);

	ApproveResDto approveClaim(ApproveReqDto approveReqDto);

}
