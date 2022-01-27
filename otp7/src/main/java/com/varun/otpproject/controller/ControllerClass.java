package com.varun.otpproject.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.varun.otpproject.exception.AttemptFailedException;
import com.varun.otpproject.exception.InvalidEmailException;
import com.varun.otpproject.exception.InvalidOTPException;
import com.varun.otpproject.exception.OTPExpireException;
import com.varun.otpproject.exception.OrderIDNotFoundException;
import com.varun.otpproject.exception.OtpGeneratedSuccessfully;
import com.varun.otpproject.exception.OtpValidatedSuccessfully;
import com.varun.otpproject.exception.UserIDNotFoundException;
import com.varun.otpproject.service.ServiceInterface;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

// Here are my APIs--->

@RestController
@RequestMapping("/api")
public class ControllerClass {

	Logger logger = LogManager.getLogger(ControllerClass.class);
	@Autowired
	ServiceInterface service;

	
	@GetMapping("/monitor")
	public String monitor() {
		try {
			boolean condition = true;
			while (condition) {
				Runnable r = () -> {
					while (true) {

					}
				};
				new Thread(r).start();
				Thread.sleep(5000);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "OTP api monitor running";
	}

	// Post request for sending the OTP to the mentioned userID
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Alll going perfect"),
			@ApiResponse(responseCode = "500", description = "Internal service error"),
			@ApiResponse(responseCode = "404", description = "Something Went Wrong"), })
	@PostMapping("/v1/generateOTP")
	public String generateOTP(@RequestParam String userId, @RequestParam String orderId) throws UserIDNotFoundException,
			OrderIDNotFoundException, OTPExpireException, InvalidEmailException, OtpGeneratedSuccessfully {
		// mention values
		logger.info("Inside the post mapping" + userId);
		int otp = service.generateOTP(userId, orderId);
		String otpMessage = String.valueOf(otp);
		throw new OtpGeneratedSuccessfully(otpMessage);
	}

	// Get request for validating the OTP
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Alll going perfect"),
			@ApiResponse(responseCode = "500", description = "Internal service error"),
			@ApiResponse(responseCode = "404", description = "Something Went Wrong"), })
	@GetMapping("/v1/validateOTP")
	public String validateOTP(@RequestParam String userId, @RequestParam String orderId, @RequestParam int otp)
			throws UserIDNotFoundException, OrderIDNotFoundException, InvalidOTPException, OTPExpireException,
			AttemptFailedException, OtpValidatedSuccessfully {
		logger.info("Inside the get mapping");
		String message = service.validateOTP(userId, orderId, otp);
		throw new OtpValidatedSuccessfully(message);
	}
}
