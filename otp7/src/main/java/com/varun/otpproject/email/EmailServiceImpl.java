package com.varun.otpproject.email;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

// For Sending the OTP to email
@Component
public class EmailServiceImpl implements EmailServiceInterface {
 
	// Here i am using javamailsender
	@Autowired
	private JavaMailSender emailSender;

	public void sendSimpleMessage(String to, String subject, int text) {
 
		SimpleMailMessage message = new SimpleMailMessage();
		// Email will be send from this emailid
		message.setFrom("whyworry2511@gmail.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(Integer.toString(text));
		emailSender.send(message);

	}

	public boolean emailValid(String email) {
		// Regular Expression
		String regex = "^(.+)@(.+)$";
		// Compile regular expression to get the pattern
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
} 