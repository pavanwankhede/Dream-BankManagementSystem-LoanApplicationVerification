package com.dbms.loanapplicationandvarification.main.serviceimpl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dbms.loanapplicationandvarification.main.model.AllPersonalDocuments;
import com.dbms.loanapplicationandvarification.main.model.Customer;
import com.dbms.loanapplicationandvarification.main.repository.AccountDetailsRepository;
import com.dbms.loanapplicationandvarification.main.repository.AllPersonalDocumentsRepository;
import com.dbms.loanapplicationandvarification.main.repository.CibilScoreRepository;
import com.dbms.loanapplicationandvarification.main.repository.CustomerAddressRepository;
import com.dbms.loanapplicationandvarification.main.repository.CustomerRepository;
import com.dbms.loanapplicationandvarification.main.repository.CustomerVarificationRepository;
import com.dbms.loanapplicationandvarification.main.repository.DependentInfoRepository;
import com.dbms.loanapplicationandvarification.main.repository.GuarantorDetailsRepository;
import com.dbms.loanapplicationandvarification.main.repository.LocalAddressRepository;
import com.dbms.loanapplicationandvarification.main.repository.PermanentAddressRepository;
import com.dbms.loanapplicationandvarification.main.serviceI.LoanApplicationVerificationServiceI;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;



@Service
public class LoanApplicationVerificationServiceImpl implements LoanApplicationVerificationServiceI{
	
	 @Autowired
	    private CustomerRepository customerRepository;
	    
	    @Autowired
	    private AllPersonalDocumentsRepository documentsRepository;

	    private static final Logger log = LoggerFactory.getLogger(LoanApplicationVerificationServiceImpl.class);

	    @Override
	    public Customer saveCustomerData(Customer customerData, MultipartFile passportPhoto, MultipartFile addressProof,
	                                     MultipartFile panCard, MultipartFile aadharCard, MultipartFile incomeTaxCertificate,
	                                     MultipartFile salarySlip, MultipartFile signaturePhoto) {
	        try {
	            log.info("Processing new customer data...");

	            // Create and set document details
	            AllPersonalDocuments personalDocs = new AllPersonalDocuments();
	            personalDocs.setPassportPhoto(passportPhoto.getBytes());
	            personalDocs.setAddressProof(addressProof.getBytes());
	            personalDocs.setPanCard(panCard.getBytes());
	            personalDocs.setAadharCard(aadharCard.getBytes());
	            personalDocs.setIncomeTaxCertificate(incomeTaxCertificate.getBytes());
	            personalDocs.setSalarySlip(salarySlip.getBytes());
	            personalDocs.setSignaturePhoto(signaturePhoto.getBytes());

	            // Save documents and associate with customer
	            AllPersonalDocuments savedDocs = documentsRepository.save(personalDocs);

	            // Set saved documents to the customer object
	            customerData.setAllPersonalDocuments(savedDocs); // Replace with actual method name in Customer class

	            // Save Customer Data
	            Customer savedCustomer = customerRepository.save(customerData);
	            log.info("Customer data saved successfully with ID: {}", savedCustomer.getCustomerId());

	            return savedCustomer;

	        } catch (IOException e) {
	            log.error("Error processing uploaded files", e);
	            throw new RuntimeException("Failed to process uploaded files", e);
	        } catch (IllegalArgumentException e) {
	            log.error("Invalid file input", e);
	            throw new RuntimeException("Invalid file input: " + e.getMessage());
	        }
	    }
	
}


