package com.dbms.loanapplicationandvarification.main.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.dbms.loanapplicationandvarification.main.model.Customer;
import com.dbms.loanapplicationandvarification.main.model.CustomerAddress;
import com.dbms.loanapplicationandvarification.main.model.CustomerVerification;


@Service
public class EmailDetails {
	 private static final Logger log = LoggerFactory.getLogger(EmailDetails.class);
	@Autowired
    private JavaMailSender sender;
    
    @Value("${spring.mail.username}")
    private String FROM_MAIL;
	public void sendLoanVerificationEmail(Customer customer,CustomerVerification customerVerification,CustomerAddress permanentAddress) {

		
	    SimpleMailMessage message = new SimpleMailMessage();
	    message.setFrom(FROM_MAIL);
	    message.setTo(customer.getEmailId());
	    message.setSubject("Loan Verification Status - " + customer.getCustomerId());

	    message.setText(
	        "Dear " + customer.getFirstName() + ""+customer.getLastName()+"\n\n" +
	        
	        "Thank you for submitting your loan application. We have completed the initial verification process. Here are the details of your loan application:\n\n" +
	        
	        "📌 **Loan Application Details**:\n" +
	        "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
	        "🆔 **Loan ID**: " + customer.getCustomerId() + "\n" +
	        "💰 **Loan Amount**: " + customer.getTotalLoanRequired() + "\n" +
	        "📅 **Application Date**: " + customerVerification.getDate() + "\n" +
	        "📊 **Verification Status**: " + customerVerification.getVerificationStatus() + "\n" +
	        "📢 **Remarks**: " + customerVerification.getRemark() + "\n\n" +
	 
	        "📌 **Customer Details**:\n" +
	        "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
	        "👤 **Full Name**: " + customer.getFirstName() + ""+customer.getLastName()+"\n" +
	        "📞 **Contact No**: " + customer.getMobileNo() + "\n" +
	        "📧 **Email**: " + customer.getEmailId() + "\n" +
	       
	        "🏠 **Address**: " + permanentAddress.getPermanentAddress() + "\n\n" +
	        
	        "We will be reviewing your application and will notify you regarding the next steps soon. Should we need any additional documentation, we will contact you directly.\n\n" +
	        
	        "📢 **Next Steps**:\n" +
	        "If everything looks good, we will proceed with the next phase of processing your loan.\n" +
	        "In case any further documents or information are required, you will be informed accordingly.\n\n" +
	        
	        "📢 **Important**: If you have any questions or concerns, feel free to reach out to our support team at [support@bank.com].\n\n" +
	        
	        "Best Regards,\n" +
	        "📧 Loan Processing Team\n" +
	        "🏦 [Bank Name] Financial Services\n\n" +
	        
	        "✨ Thank You for Choosing Us! ✨"
	    );

	    sender.send(message);

	    log.info("Loan verification email sent to {}", customer.getEmailId());
	}

}
