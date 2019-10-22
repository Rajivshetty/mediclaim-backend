package com.medical.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.dto.DiseaseResponseDto;
import com.medical.entity.Disease;
import com.medical.exception.DiseaseNotFoundException;
import com.medical.repository.DiseaseRepository;
import com.medical.util.MedicalClaimConstants;

/**
 * @author Abhishek C
 * @apiNote DiseaseServiceImpl used to fetch diseases
 * 
 */
@Service
public class DiseaseServiceImpl implements DiseaseService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DiseaseServiceImpl.class);
	
	@Autowired
	DiseaseRepository diseaseRepository;
	
	/**
	 * @author Abhishek C
	 * @apiNote getDiseaseList method to fetch list of diseases
	   @return list of hospitals
	   @throws DiseaseNotFoundException
	 */
	@Override
	public List<DiseaseResponseDto> getDiseaseList() {
		LOGGER.debug("DiseaseServiceImpl getDiseaseList()");
		List<Disease> list = diseaseRepository.findAll();
		if(list.isEmpty()) {
			throw new DiseaseNotFoundException(MedicalClaimConstants.DISEASE_NOT_FOUND);
		}
		List<DiseaseResponseDto> diseasesList = new ArrayList<>();
		list.stream().forEach(e ->{
			DiseaseResponseDto response = new DiseaseResponseDto();
			BeanUtils.copyProperties(e, response);
			diseasesList.add(response);
		});
		return diseasesList;
	}

}
