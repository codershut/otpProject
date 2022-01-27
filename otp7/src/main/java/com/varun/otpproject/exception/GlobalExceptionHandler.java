
package com.varun.otpproject.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserIDNotFoundException.class)
    public ResponseEntity<?> userIDNotFoundException(UserIDNotFoundException ex, WebRequest request) {
         ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
         System.out.println(errorDetails);
         return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
 	 
	@ExceptionHandler(OrderIDNotFoundException.class)
    public ResponseEntity<?> orderIDNotFoundException(OrderIDNotFoundException ex, WebRequest request) {
         ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
         System.out.println(errorDetails);
         return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
	   
	@ExceptionHandler(InvalidOTPException.class)
    public ResponseEntity<?> invalidOTPException(InvalidOTPException ex, WebRequest request) {
         ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
         System.out.println(errorDetails);
         return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(OTPExpireException.class)
    public ResponseEntity<?> otpExpireException(OTPExpireException ex, WebRequest request) {
         ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
         System.out.println(errorDetails);
         return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globleExceptionHandler(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        System.out.println(errorDetails);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<?> invalidEmailException(InvalidEmailException ex, WebRequest request) {
         ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
         System.out.println(errorDetails);
         return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AttemptFailedException.class)
    public ResponseEntity<?> attemptFailedException(AttemptFailedException ex, WebRequest request) {
         ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
         System.out.println(errorDetails);
         return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
   
	
}
