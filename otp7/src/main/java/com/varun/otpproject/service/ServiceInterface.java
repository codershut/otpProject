package com.varun.otpproject.service;

import com.varun.otpproject.exception.AttemptFailedException;
import com.varun.otpproject.exception.InvalidEmailException;
import com.varun.otpproject.exception.InvalidOTPException;
import com.varun.otpproject.exception.OTPExpireException;
import com.varun.otpproject.exception.OrderIDNotFoundException;
import com.varun.otpproject.exception.UserIDNotFoundException;

public interface ServiceInterface {
	public int generateOTP(String userId,String orderId) throws UserIDNotFoundException,OrderIDNotFoundException,OTPExpireException, InvalidEmailException;
	public String validateOTP(String userId,String orderId,int otp) throws UserIDNotFoundException,OrderIDNotFoundException,InvalidOTPException,OTPExpireException, AttemptFailedException;
}
