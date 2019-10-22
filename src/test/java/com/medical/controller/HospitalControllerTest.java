package com.medical.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medical.dto.HospitalResponseDto;
import com.medical.service.HospitalService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = HospitalController.class)
public class HospitalControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	HospitalService hospitalService;
	
	HospitalResponseDto responseDto;
	List<HospitalResponseDto> list;
	
	/**
	 * @author Abhishek C
	 * Initial set up
	 */
	@Before
	public void setup() {
		responseDto = new HospitalResponseDto();
		list = new ArrayList<HospitalResponseDto>();
		responseDto.setHospitalId(1);
		responseDto.setHospitalName("AIIMS ( All India Institutes of Medical Sciences)");
		list.add(responseDto);
	}
	
	@Test
	public void testGetHospitals() throws JsonProcessingException, Exception {
		Mockito.when(hospitalService.getHospitalList()).thenReturn(list);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/hospitals/").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(list))).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	public static String asJsonString(final Object obj) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(obj);
	}
}
