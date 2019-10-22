/**
 * 
 */
package com.medical.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author user1
 *
 */
public class MedicalClaimConstants {

	private MedicalClaimConstants() {
	}

	public static final String CREDENTIALS_EMPTY = "UserName or Password cannot be empty";
	public static final String LOGIN_SUCCESS = "Logged In Successfully";
	public static final String LOGIN_FAILURE = "Incorrect Username or password";
	public static final Integer LOGIN_SUCCESS_CODE = 201;
	public static final Integer LOGIN_FAILURE_CODE = 401;
	public static final String NO_DISEASE_FOUND = "No Disease found with given Id";
	public static final String INVALID_CLAIM_AMOUNT = "Your Claim Amount is greater than the permitted amount ";
	public static final String INVALID_DATE = "Admitted date can't be after Discharge Date ";
	public static final String NO_USER = "Invalid UserId";
	public static final String CLAIM_REQUEST_STATUS = "PASS";
	public static final String CLAIM_REQUEST_PASS = "Your Claim Request Is passed";
	public static final Integer CLAIM_REQUEST_SUCCESS_CODE = 201;
	public static final String UPLOADED_FOLDER = "c://tempo//";

	public static  String proofUpload(MultipartFile file, RedirectAttributes redirectAttributes) {

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:uploadStatus";
		}

		try {

			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			Path path = Paths.get(MedicalClaimConstants.UPLOADED_FOLDER + file.getOriginalFilename());

			Files.write(path, bytes);

			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");

		} catch (IOException e) {

		}

		return "redirect:/uploadStatus";

	}

}
