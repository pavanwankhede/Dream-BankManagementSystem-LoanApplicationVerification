package com.dbms.loanapplicationandvarification.main.serviceI;

import org.springframework.web.multipart.MultipartFile;

import com.dbms.loanapplicationandvarification.main.model.Customer;

import jakarta.validation.Valid;

public interface LoanApplicationVerificationServiceI {

	public Customer saveCustomerData(Customer customerData, MultipartFile passportPhoto, MultipartFile addressProof,
			MultipartFile panCard, MultipartFile aadharCard, MultipartFile incomeTaxCertificate,
			MultipartFile salarySlip, MultipartFile signaturePhoto);

}
