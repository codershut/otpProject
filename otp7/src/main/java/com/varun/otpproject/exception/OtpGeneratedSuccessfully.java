package com.varun.otpproject.exception;

public class OtpGeneratedSuccessfully extends Exception {

	private static final long serialVersionUID = 1L;

	public OtpGeneratedSuccessfully(String otpMessage) {
		super(otpMessage);
	}

}
