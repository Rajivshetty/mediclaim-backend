package com.medical.controller;
/**
 *  author mahesh
 */
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.medical.dto.ApproveResDto;
import com.medical.dto.ClaimResDto;
import com.medical.entity.Claim;
import com.medical.service.ApproveService;

@RunWith(MockitoJUnitRunner.class)
public class ApproveControllerTest {

	@Mock
	ApproveService approveService;

	@InjectMocks
	ApproveController approveController;

	@Test
	public void testClaimList() {
		List<ClaimResDto> list = new ArrayList<>();

		Claim claim1 = new Claim();
		claim1.setPatientName("Raj");
		ClaimResDto claimResDto1 = new ClaimResDto();
		claimResDto1.setPatientName(claim1.getPatientName());

		Claim claim2 = new Claim();
		claim2.setPatientName("Ramana");
		ClaimResDto claimResDto2 = new ClaimResDto();
		claimResDto2.setPatientName(claim2.getPatientName());

		list.add(claimResDto1);
		list.add(claimResDto2);

		Mockito.when(approveService.claimList(2)).thenReturn(list);
		ResponseEntity<List<ClaimResDto>> obj = approveController.claimList(2);
		assertEquals(list.size(), obj.getBody().size());
	}



}
