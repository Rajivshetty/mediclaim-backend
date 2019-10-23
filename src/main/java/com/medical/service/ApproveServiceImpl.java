package com.medical.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.medical.dto.ApproveReqDto;
import com.medical.dto.ApproveResDto;
import com.medical.dto.ClaimResDto;
import com.medical.entity.Claim;
import com.medical.entity.ClaimApproval;
import com.medical.entity.Disease;
import com.medical.entity.Hospital;
import com.medical.exception.MedicalClaimException;
import com.medical.repository.ClaimApprovalRepo;
import com.medical.repository.ClaimRepo;
import com.medical.repository.DiseaseRepo;
import com.medical.repository.HospitalRepo;
import com.medical.util.MedicalClaimConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author mahesh
 *
 */
@Service
@Slf4j
public class ApproveServiceImpl implements ApproveService {

	@Autowired
	ClaimRepo claimRepo;

	@Autowired
	DiseaseRepo diseaseRepo;

	@Autowired
	HospitalRepo hospitalRepo;

	@Autowired
	ClaimApprovalRepo claimApprovalRepo;

	/**
	 * this method returns the list of claim details based on the approverId
	 * 
	 * @param approverId
	 * @return List<ClaimResDto>
	 */
	@Override
	public List<ClaimResDto> claimList(@NotNull Integer approverId) {

		log.debug("getMyApproves method in ApproveServiceImpl class");

		List<ClaimResDto> claimList = new ArrayList<>();

		List<Claim> claim = claimRepo.findAllByOrderByPatientName();
		claim.stream().forEach(c -> {
			if (approverId.equals(MedicalClaimConstants.APPROVER_ID)) {
				if (c.getApprStatus().equals(MedicalClaimConstants.PENDING)) {
					ClaimResDto cl = new ClaimResDto();
					BeanUtils.copyProperties(c, cl);
					cl.setApprStatus(c.getApprStatus());
					Optional<Disease> disease = diseaseRepo.findById(c.getDiseaseId());
					cl.setDiseaseName(disease.get().getDiseaseName());
					cl.setLimitAmount(disease.get().getLimitAmount());
					Optional<Hospital> hospital = hospitalRepo.findById(c.getHospitalId());
					cl.setHospitalName(hospital.get().getHospitalName());
					cl.setClaimId(c.getClaimId());
					claimList.add(cl);
				}
			} else {
				ClaimResDto cl = new ClaimResDto();
				BeanUtils.copyProperties(c, cl);
				cl.setApprStatus(c.getApprStatus());
				Optional<Disease> disease = diseaseRepo.findById(c.getDiseaseId());
				cl.setDiseaseName(disease.get().getDiseaseName());
				cl.setLimitAmount(disease.get().getLimitAmount());
				Optional<Hospital> hospital = hospitalRepo.findById(c.getHospitalId());
				cl.setHospitalName(hospital.get().getHospitalName());
				cl.setClaimId(c.getClaimId());
				claimList.add(cl);
			}

		});

		return claimList;
	}

	/**
	 * this method is used to approve the claim and update the status,comments
	 * 
	 * @param approverId
	 * @param claimId
	 * @param status
	 * @param comment
	 * @return ApproveResDto
	 * @throws MedicalClaimException 
	 */
	@Override
	public ApproveResDto approveClaim(ApproveReqDto approveReqDto) throws MedicalClaimException {
		log.debug("approveClaim method in ApproveServiceImpl class");
		Optional<Claim> claim = claimRepo.findByClaimId(approveReqDto.getClaimId());
		if (claim.isPresent()) {

			claim.get().setApprStatus(approveReqDto.getStatus());
			claim.get().setComments(approveReqDto.getComment());
			claim.get().setApproverId(approveReqDto.getApproverId());
			claimRepo.save(claim.get());

			ClaimApproval claimApproval = new ClaimApproval();
			claimApproval.setApproverId(approveReqDto.getApproverId());
			claimApproval.setCalimId(approveReqDto.getClaimId());
			claimApproval.setComments(approveReqDto.getComment());
			claimApproval.setStatus(approveReqDto.getStatus());
			claimApproval.setApprovedDate(LocalDate.now());
			claimApprovalRepo.save(claimApproval);

		} else {
			throw new MedicalClaimException(MedicalClaimConstants.RECORD_NOT_FOUND);
		}
		ApproveResDto approveResDto = new ApproveResDto();
		approveResDto.setMessage(MedicalClaimConstants.UPDATED);
		approveResDto.setStatusCode(HttpStatus.OK.value());
		return approveResDto;
	}

}
