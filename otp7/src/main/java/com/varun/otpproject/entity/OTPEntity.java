package com.varun.otpproject.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("otp")
public class OTPEntity {

	@Id
	private String user_id;
	private String order_id;
	private int otp;
	 
	public OTPEntity() {
	// TODO Auto-generated constructor stub
	}
 
	public OTPEntity(String user_id, String order_id, int otp) {
		super();
		this.user_id = user_id;
		this.order_id = order_id;
		this.otp = otp;
	}
 
	public String getUser_id() {
		return user_id;
	}
 
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	@Override
	public String toString() {
		return "OTPEntity [user_id=" + user_id + ", order_id=" + order_id + ", otp=" + otp + "]";
	}

	



}
