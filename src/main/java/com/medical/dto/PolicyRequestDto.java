/**
 * 
 */
package com.medical.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Akash
 * 
 *
 */
@Setter
@Getter
public class PolicyRequestDto {
	
	@Range()
	private Integer policyNo;
	private long aadharNo;
	private LocalDate dob;

}
