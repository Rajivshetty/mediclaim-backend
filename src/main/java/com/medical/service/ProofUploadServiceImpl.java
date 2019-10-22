/**
 * @author Shiva
 *
 */
package com.medical.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.medical.util.MedicalClaimConstants;

@Service
public class ProofUploadServiceImpl implements ProofUploadService {

	@Override
	public String proofUpload(MultipartFile file, RedirectAttributes redirectAttributes) {

		return MedicalClaimConstants.proofUpload(file, redirectAttributes);

	}

}
