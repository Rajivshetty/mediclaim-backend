package com.medical.controller;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.medical.service.ProofUploadServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProofUploadControllerTest {

	@Mock
	ProofUploadServiceImpl proofUploadService;
	/*
	 * @Mock File file=null;
	 */
	@InjectMocks
	ProofUploadController proofUploadController;

	RedirectAttributes redirectAttributes = null;

	@Test
	public void testSingleFileUpload() throws FileNotFoundException, IOException {
		String fileUrl = "C:\\\\Users\\\\User1\\\\Desktop.akash.txt";
		InputStream is = proofUploadController.getClass().getClassLoader().getResourceAsStream(fileUrl);
		MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "excel.txt", "multipart/form-data", is);
		Mockito.when(proofUploadService.proofUpload(Mockito.any(), Mockito.any()))
				.thenReturn("You successfully uploaded");
		ResponseEntity<String> actualValue = proofUploadController.singleFileUpload(mockMultipartFile, redirectAttributes);
		assertEquals(200, actualValue.getStatusCodeValue());
	}

	@Test(expected = FileNotFoundException.class)
	public void testSingleFileUploadNegative() throws FileNotFoundException, IOException {
		MultipartFile multipartFile = new MockMultipartFile("Net.docx",
				new FileInputStream(new File("C://Users//user1//Desktop//mahesh//Net.docx")));
		Mockito.when(proofUploadService.proofUpload(Mockito.any(), Mockito.any()))
				.thenReturn("You successfully uploaded");
		ResponseEntity<String> actualValue = proofUploadController.singleFileUpload(multipartFile, redirectAttributes);
		assertEquals(200, actualValue.getStatusCodeValue());
	}

}
