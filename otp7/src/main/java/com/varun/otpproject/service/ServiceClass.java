package com.varun.otpproject.service;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.varun.otpproject.dao.OTPDao;
import com.varun.otpproject.email.EmailServiceInterface;
import com.varun.otpproject.entity.OTPEntity;
import com.varun.otpproject.exception.AttemptFailedException;
import com.varun.otpproject.exception.InvalidEmailException;
import com.varun.otpproject.exception.InvalidOTPException;
import com.varun.otpproject.exception.OTPExpireException;
import com.varun.otpproject.exception.OrderIDNotFoundException;
import com.varun.otpproject.exception.UserIDNotFoundException;

@Service
public class ServiceClass implements ServiceInterface {

	@Autowired
	OTPDao otpDao;

	@Autowired
	public EmailServiceInterface emailService;

	Logger logger = LogManager.getLogger(ServiceClass.class);
	static int attempt = 0;
	// Here i am generating the OTP by taking UserID and OrderID from the user.
   
	public int generateOTP(String userId, String orderId)
			throws UserIDNotFoundException, OrderIDNotFoundException, OTPExpireException, InvalidEmailException {
		// In case if the email is invalid, It will throw the invalid email exception
		System.out.println("Email is valid-    "+emailService.emailValid(userId));
		OTPEntity newotp = otpDao.findById(userId).orElse(null);
		if(newotp==null) {
			System.out.println("OTP is null -----------------------");
		} 
		if (emailService.emailValid(userId)) {
			OTPEntity otpFind = otpDao.findById(userId).orElseThrow(
					() -> new UserIDNotFoundException("Invalid UserId, Please check it again : " + userId));
			if (otpFind.getOrder_id().equals(orderId)) {
				int otp = (new Random().nextInt(900000)) + 100000;
				attempt=0;
				OTPEntity otpObject = new OTPEntity(userId, orderId, otp);
				otpDao.save(otpObject);
				
				new Thread(()->{
					emailService.sendSimpleMessage(userId, "Here is the OTP", otpObject.getOtp());
				}).start();
								// This will be invoked after the defined seconds and the value of OTP in the DB
				// will be changed to something negative
				logger.info("Sending mail to the user :)");
				Timer t = new Timer();
				TimerTask tt = new TimerTask() {
					@Override
					public void run() {
						otpFind.setOtp(-(100000 + (new Random().nextInt(900000))));
						otpDao.save(otpFind);
					};
				};
				t.schedule(tt, 120000);

				return otp;
			} else {
				logger.info("ORDER ID is not valid--Inside GenerateOTP method");
				throw new OrderIDNotFoundException("Invalid OrderID, Please check it again: " + orderId);
			}
		} else {
			throw new InvalidEmailException("Your email is invalid:" + userId);
		}
 
	}
	// Here i am validating the OTP which is entered by user.

	public String validateOTP(String userId, String orderId, int otp) throws UserIDNotFoundException,
			OrderIDNotFoundException, InvalidOTPException, OTPExpireException, AttemptFailedException {
		logger.info("OTP is validating.....");
		OTPEntity otpFind = otpDao.findById(userId)
				.orElseThrow(() -> new UserIDNotFoundException("Invalid UserId : " + userId));
		if (attempt >= 3) {
			throw new AttemptFailedException("Maxmimum attempts excedded, Please regenerate the OTP");
		}
		if (otpFind.getOrder_id().equals(orderId) && otpFind.getOtp() == otp && attempt<3) {
			return "Wooyah! OTP is matched :)";
		} else if (otpFind.getOtp() < 0) {
			throw new OTPExpireException("OTP is Expired, Please resend :(");
		} else {
			attempt++;
			throw new InvalidOTPException("Invalid OTP. Please enter the correct one");
		}
	}
 
}
