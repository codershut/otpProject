package com.varun.otpproject.email;

public interface EmailServiceInterface {
	public void sendSimpleMessage(String to, String subject, int text);

	public boolean emailValid(String email);
}
