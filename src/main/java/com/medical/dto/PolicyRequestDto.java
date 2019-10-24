/**
 * 
 */
package com.medical.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

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
	@NotNull
	private Integer policyNo;
	@NotNull
	private long aadharNo;
	@NotNull
	private LocalDate dob;

}
