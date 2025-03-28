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

		    String emailBody = "Dear " + customer.getFirstName() + " " + customer.getLastName() + ",\n\n" +
		            "Thank you for submitting your loan application. Below are the details:\n\n" +
		            "📌 **Loan Application Details**:\n" +
		            "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
		            "🆔 **Loan ID**: " + customer.getCustomerId() + "\n" +
		            "💰 **Loan Amount**: " + customer.getTotalLoanRequired() + "\n" +
		            "📅 **Application Date**: " + customerVerification.getVerificationDate() + "\n" +
		            "📅 **Application Time**: " + customerVerification.getVerificationTime() + "\n" +
		            "📢 **Remarks**: " + customerVerification.getRemark() + "\n\n" +
		            "📌 **Customer Details**:\n" +
		            "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
		            "👤 **Full Name**: " + customer.getFirstName() + " " + customer.getLastName() + "\n" +
		            "📞 **Contact No**: " + customer.getMobileNo() + "\n" +
		            "📧 **Email**: " + customer.getEmailId() + "\n" +
		            "🏠 **Address**: " + permanentAddress.getPermanentAddress() + "\n\n";

		    // Customizing message based on status
		    switch (customerVerification.getVerificationStatus()) {
		        case PENDING:
		            emailBody += "🕐 Your loan application is currently under review. We will notify you once the verification is completed.\n\n";
		            break;
		        case APPROVED:
		            emailBody += "✅ Congratulations! Your loan application has been approved. We will contact you with further details soon.\n\n";
		            break;
		        case REJECTED:
		            emailBody += "❌ Unfortunately, your loan application has been rejected. Please check the remarks for more details.\n\n";
		            break;
		    }

		    emailBody += "📢 **Next Steps**:\n" +
		            "If any further documents or information are required, you will be informed accordingly.\n\n" +
		            "📢 **Important**: If you have any questions, feel free to reach out to our support team at [support@bank.com].\n\n" +
		            "Best Regards,\n" +
		            "📧 Loan Processing Team\n" +
		            "🏦 [Bank Name] Financial Services\n\n" +
		            "✨ Thank You for Choosing Us! ✨";

		    message.setText(emailBody);
		    sender.send(message);

		    log.info("Loan verification email sent to {}", customer.getEmailId());
		}

}
