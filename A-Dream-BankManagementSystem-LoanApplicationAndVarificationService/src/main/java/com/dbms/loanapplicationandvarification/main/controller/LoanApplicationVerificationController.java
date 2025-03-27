package com.dbms.loanapplicationandvarification.main.controller;

import java.io.IOException;
import java.sql.Time;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dbms.loanapplicationandvarification.main.model.Customer;
import com.dbms.loanapplicationandvarification.main.serviceI.LoanApplicationVerificationServiceI;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/loanapplication")
public class LoanApplicationVerificationController {
	
	private static final Logger log= LoggerFactory.getLogger(LoanApplicationVerificationController.class); 
	
	@Autowired
	private LoanApplicationVerificationServiceI appvarificationServiceI;
	
	@Autowired
	private ObjectMapper mapper;
	
	@PostMapping("/addCustomer")
	public ResponseEntity<?> addCustomerData(
	        @Valid @RequestPart("customerData") String cusDataJson,
	        @RequestPart("passportPhoto") MultipartFile passportPhoto,
	        @RequestPart("addressProof") MultipartFile addressProof,
	        @RequestPart("panCard") MultipartFile panCard,
	        @RequestPart("aadharCard") MultipartFile aadharCard,
	        @RequestPart("incomeTaxCertificate") MultipartFile incomeTaxCertificate,
	        @RequestPart("salarySlip") MultipartFile salarySlip,
	        @RequestPart("signaturePhoto") MultipartFile signaturePhoto) {

	    try {
	        log.info("Received request to save customer data.");
	        log.info("Received JSON: {}", cusDataJson);

	        if (cusDataJson == null || cusDataJson.trim().isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer JSON data is missing or empty.");
	        }

	        // Convert JSON string to Customer object
	        Customer customerData = mapper.readValue(cusDataJson, Customer.class);
	        log.info("Successfully parsed customer data: {}", customerData);

	        // Save customer data with documents
	        Customer savedCustomer = appvarificationServiceI.saveCustomerData(
	                customerData, passportPhoto, addressProof, panCard, aadharCard, incomeTaxCertificate, salarySlip, signaturePhoto
	        );

	        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);

	    } catch (IOException e) {
	        log.error("Error processing customer data JSON or files", e);
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid customer data format.");
	    }
}
}
