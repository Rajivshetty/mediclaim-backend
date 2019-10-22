package com.medical.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApproveReqDto {
	private Integer approverId;
	private Integer claimId;
	private String status;
	private String comment;
}
