package com.varun.otpproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

// From here our program execution STARTS---->
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
@OpenAPIDefinition
public class OtpProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(OtpProjectApplication.class, args);
	}
}
       