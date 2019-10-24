package com.medical.dto;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * 
 * @author mahesh
 *
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ApproveResDto {
	
	private String message;
	private Integer statusCode;
}
