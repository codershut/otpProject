package com.varun.otpproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)

public class AttemptFailedException extends Exception {

	private static final long serialVersionUID = 1L;
	public AttemptFailedException(String string) {
		super(string);
	}

}
  