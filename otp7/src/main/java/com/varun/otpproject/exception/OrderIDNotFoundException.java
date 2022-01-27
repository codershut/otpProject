package com.varun.otpproject.exception;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class OrderIDNotFoundException  extends Exception{
	

	private static final long serialVersionUID = 1L;

	public OrderIDNotFoundException(String errorMessage)
	{
		super(errorMessage);
		System.out.println(errorMessage);
	}

}
   