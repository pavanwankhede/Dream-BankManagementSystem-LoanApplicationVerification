package com.dbms.loanapplicationandvarification.main.serviceimpl;

import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.dbms.loanapplicationandvarification.main.email.EmailDetails;
import com.dbms.loanapplicationandvarification.main.email.VerificationRemarkGenerator;
import com.dbms.loanapplicationandvarification.main.enums.VerificationStatus;
import com.dbms.loanapplicationandvarification.main.exceptions.CustomerNotFoundException;
import com.dbms.loanapplicationandvarification.main.exceptions.EntityNotFoundException;
import com.dbms.loanapplicationandvarification.main.exceptions.ValidationExceptions;
import com.dbms.loanapplicationandvarification.main.model.AllPersonalDocuments;
import com.dbms.loanapplicationandvarification.main.model.Customer;
import com.dbms.loanapplicationandvarification.main.model.CustomerAddress;
import com.dbms.loanapplicationandvarification.main.model.CustomerVerification;
import com.dbms.loanapplicationandvarification.main.repository.AllPersonalDocumentsRepository;
import com.dbms.loanapplicationandvarification.main.repository.CustomerRepository;
import com.dbms.loanapplicationandvarification.main.repository.CustomerVarificationRepository;
import com.dbms.loanapplicationandvarification.main.serviceI.LoanApplicationVerificationServiceI;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;



@Service
public class LoanApplicationVerificationServiceImpl implements LoanApplicationVerificationServiceI{
	
    
 @Autowired
 EmailDetails email;
	
	@Autowired
	private Validator validator;
	
	 @Autowired
	    private CustomerRepository customerRepository;

	 @Autowired
	 private CustomerVarificationRepository varificationRepository;
	 
	    @Autowired
	    private AllPersonalDocumentsRepository documentsRepository;

	    private static final Logger log = LoggerFactory.getLogger(LoanApplicationVerificationServiceImpl.class);

	    @Override
	    public Customer saveCustomerData(Customer customerData, MultipartFile passportPhoto, MultipartFile addressProof,
	                                     MultipartFile panCard, MultipartFile aadharCard, MultipartFile incomeTaxCertificate,
	                                     MultipartFile salarySlip, MultipartFile signaturePhoto) {
	        try {
	        	 validateUser(customerData);
	        	 
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
                  
	            
	         // **Create and attach CustomerVerification object**
	            CustomerVerification verification = new CustomerVerification();
	            verification.setVerificationStatus(VerificationStatus.PENDING);
	            verification.setRemark(VerificationRemarkGenerator.generateRemark(VerificationStatus.PENDING));
	            verification.setVerificationDate(new Date());  
	            verification.setVerificationTime(new Time(System.currentTimeMillis())); 

	            // **Attach verification to customer**
	            customerData.setCustomerVerification(verification);
 
	            // Save Customer Data
	            Customer savedCustomer = customerRepository.save(customerData);
	            if(savedCustomer !=null) {
	            	try {
	            	
	  email.sendLoanVerificationEmail(savedCustomer,savedCustomer.getCustomerVerification(), savedCustomer.getCustomerAddress());
	       		log.info("Loan Verification confirmation email sent successfully.");
		         } catch (Exception e) {
		              log.error("Failed to send Loan Verification confirmation email: {}", e.getMessage(), e);
		     }
	            }
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
	
	    
	    private void validateUser(Customer customer) {
	        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
	        
	        if (!violations.isEmpty()) {
	            Map<String, String> errors = new HashMap<>();
	            for (ConstraintViolation<Customer> violation : violations) {
	                errors.put(violation.getPropertyPath().toString(), violation.getMessage());
	            }
	            throw new ValidationExceptions(errors); // Throw custom validation exception
	        }

	    }


		@Override
		public List<Customer> getAllCustomerData() {
			log.info("Fetching all Customer Data.");
			return customerRepository.findAll();
		}


		@Override
		public Customer getCustomerById(int id) {
			Optional<Customer> customer=customerRepository.findById(id);
			if(customer.isEmpty())
			{
				throw new CustomerNotFoundException("Custmoer For This ID Is Not Found : " +id);
			}
			return customer.get();
		}
		
		
		@Override
		public boolean updateVerificationStatus(int verificationId, VerificationStatus newStatus) {
			
		    CustomerVerification verification = varificationRepository.findById(verificationId)
		        .orElseThrow(() -> new EntityNotFoundException("Verification record not found for ID: " + verificationId));

		    verification.setVerificationStatus(newStatus);
		    varificationRepository.save(verification);

		    if (verification.getCustomer() != null) {
		        Customer customer = verification.getCustomer();
		        email.sendCustomerVerificationStatusUpdate(customer, newStatus);
		        log.info("Status update email sent for customer ID: {}", customer.getCustomerId());
		    } else {
		        log.warn("No associated customer found for verification ID: {}", verificationId);
		    }

		    return true;
		}
		
		@Override
		@Transactional
		public boolean deleteCustomerByIdAndStatus(int customerId, VerificationStatus status) {
		    Customer customer = customerRepository.findById(customerId).orElse(null);

		    if (customer == null || !customer.getCustomerVerification().getVerificationStatus().equals(status)) {
		        log.warn("No REJECTED customer found with ID {}", customerId);
		        return false;
		    }

		    log.info("Deleting customer with ID {} and related data.", customerId);
		    customerRepository.delete(customer);  // Deletes customer and all related data due to cascading
		    return true;
		}

}