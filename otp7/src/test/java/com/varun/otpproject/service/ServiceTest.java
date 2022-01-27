
package com.varun.otpproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.varun.otpproject.dao.OTPDao;
import com.varun.otpproject.email.EmailServiceInterface;
import com.varun.otpproject.exception.AttemptFailedException;
import com.varun.otpproject.exception.InvalidEmailException;
import com.varun.otpproject.exception.InvalidOTPException;
import com.varun.otpproject.exception.OTPExpireException;
import com.varun.otpproject.exception.OrderIDNotFoundException;
import com.varun.otpproject.exception.UserIDNotFoundException;

@SpringBootTest
class ServiceTest {

	@Autowired
	private ServiceClass testingService;

	@Autowired
	private EmailServiceInterface emailService;

	@Autowired
	private OTPDao dao;

	
	  @Test
	  
	  @DisplayName("Length of OTP") 
	  void testGenerateOTP() throws UserIDNotFoundException, OrderIDNotFoundException, OTPExpireException,InvalidEmailException { 
	 
	  int expected = testingService.generateOTP("111codershut@gmail.com", "12");
	  String val= String.valueOf(expected); 
	  assertEquals(val.length(), 6);
	  }
	  
	  //fail("Not yet implemented"); }
	 
	  @Test
	  
	  @DisplayName("Format") void testGenerateOTP1() throws UserIDNotFoundException, OrderIDNotFoundException, OTPExpireException,
	  InvalidEmailException { 
      int expected =testingService.generateOTP("111codershut@gmail.com", "12"); 
      String val = String.valueOf(expected); assertTrue(val.matches("[0-9]+")); 
      }
	 
	 @Test
	  
	  @DisplayName("Valid Email") void testEmail() { boolean res =
	  emailService.emailValid("111codershut@gmail.com"); assertTrue(res); }
	 

	

	
	
	
	 @Test
	  
	  @DisplayName("Test invalid email") void testInvalidEmail() {
	  assertThrows(InvalidEmailException.class, () -> {
	  testingService.generateOTP("yeyeeghvegve", "12"); }, "ggh");
	  
	  }
	  
	 // @Test
	  
//	  @DisplayName("OTP expire check") void testOtpExpireExeption() {
//	  assertThrows(OTPExpireException.class, () -> {
//	  int genOtp=testingService.generateOTP("himevarun@gmail.com", "13");
//	  int exp=-genOtp;
//	  testingService.validateOTP("umang.08.tyagi@gmail.com", "14", exp); }, "ggh"); }
	 
	  @Test
	  
	  @DisplayName("User ID Not found exception check") void
	 testUserNotFoundExeption() { assertThrows(UserIDNotFoundException.class, ()
	  -> { testingService.generateOTP("himetarun@gmail.com", "15"); }, "ggh"); }
	  
	 @Test
	 
	  @DisplayName("Order ID Not found exception check") void
	  testOrderIdNotFoundException() { assertThrows(OrderIDNotFoundException.class,
	  () -> { testingService.generateOTP("himevarun@gmail.com", "666"); }, "ggg");
	  }
	
	
	@Test
	void testgenerate() throws UserIDNotFoundException, OrderIDNotFoundException, OTPExpireException, InvalidEmailException {
			int expectedOtp=testingService.generateOTP("himevarun@gmail.com","13");
			int actualOtp=dao.findById("himevarun@gmail.com").get().getOtp();
		assertEquals(expectedOtp, actualOtp);
	}
	
	@Test
	void testValidate() throws UserIDNotFoundException, OrderIDNotFoundException, InvalidOTPException, OTPExpireException, AttemptFailedException, InvalidEmailException
	{
		int genOtp=testingService.generateOTP("himevarun@gmail.com", "13");
		int num=dao.findById("himevarun@gmail.com").get().getOtp();
		String message =testingService.validateOTP("himevarun@gmail.com", "13",num);
		String expectedMessage="Wooyah! OTP is matched :)";
		assertEquals(message, expectedMessage);
	}
	
}
