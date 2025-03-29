package com.dbms.loanapplicationandvarification.main.serviceI;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dbms.loanapplicationandvarification.main.enums.VerificationStatus;
import com.dbms.loanapplicationandvarification.main.model.Customer;
import com.dbms.loanapplicationandvarification.main.model.CustomerVerification;

import jakarta.validation.Valid;

public interface LoanApplicationVerificationServiceI {

	public Customer saveCustomerData(Customer customerData, MultipartFile passportPhoto, MultipartFile addressProof,
			MultipartFile panCard, MultipartFile aadharCard, MultipartFile incomeTaxCertificate,
			MultipartFile salarySlip, MultipartFile signaturePhoto);

	public List<Customer> getAllCustomerData();

	public Customer getCustomerById(int id);

	// public CustomerVerification updateVerificationStatus(int verificationId, VerificationStatus status);

	public boolean deleteCustomerByIdAndStatus(int customerId, VerificationStatus status);

	public boolean updateVerificationStatus(int verificationId, VerificationStatus status);

	public Customer updateCustomerData(int customerId, Customer updatedCustomerData, MultipartFile passportPhoto,
			MultipartFile addressProof, MultipartFile panCard, MultipartFile aadharCard,
			MultipartFile incomeTaxCertificate, MultipartFile salarySlip, MultipartFile signaturePhoto);



}
