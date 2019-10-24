package com.medical.dto;

import javax.validation.constraints.NotNull;

/**
 * author mahesh
 */
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApproveReqDto {
	@NotNull
	private Integer approverId;
	@NotNull
	private Integer claimId;
	@NotNull
	private String status;
	@NotNull
	private String comment;
}
