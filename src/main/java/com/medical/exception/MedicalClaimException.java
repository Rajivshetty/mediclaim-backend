package com.medical.exception;

public class MedicalClaimException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MedicalClaimException(String message) {
		super(message);
	}

}