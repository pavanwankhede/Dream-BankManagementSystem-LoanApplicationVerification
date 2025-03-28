package com.dbms.loanapplicationandvarification.main.exceptions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.dbms.loanapplicationandvarification.main.dto.ErrorResponseDTO;

@RestControllerAdvice
public class GlobalExceptions {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new LinkedHashMap<>();
	    errors.put("message", "Validation failed. Please check the input.");
	    errors.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	    errors.put("time", new SimpleDateFormat("HH:mm:ss").format(new Date()));
           

	 // Add field-specific validation messages
	 	    ex.getFieldErrors().forEach(error -> 
	 	        errors.put(error.getField(), error.getDefaultMessage())
	 	    );
	    

	    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDTO> handleException(Exception e) {
	    ErrorResponseDTO errorResponse = new ErrorResponseDTO(e.getMessage());
	    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	 @ExceptionHandler(ValidationExceptions.class)
	    public ResponseEntity<ErrorResponseDTO> handleValidationException(ValidationExceptions ex) {
	        Map<String, String> errors = ex.getErrors();

	        // Create a formatted error response
	        ErrorResponseDTO errorResponse = new ErrorResponseDTO("Validation Failed");
	        errorResponse.setMessage(errors.toString()); // Convert map to string for display

	        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	    }
	 
	 @ExceptionHandler(CustomerNotFoundException.class)
		public ResponseEntity<ErrorResponseDTO> handleEnquiryNotFoundException( CustomerNotFoundException e) {
		    ErrorResponseDTO errorResponse = new ErrorResponseDTO(e.getMessage());
		    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}
	
}
