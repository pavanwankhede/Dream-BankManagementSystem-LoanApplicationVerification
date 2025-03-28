package com.dbms.loanapplicationandvarification.main.email;

import com.dbms.loanapplicationandvarification.main.enums.VerificationStatus;

public class VerificationRemarkGenerator {
	
	public static String generateRemark(VerificationStatus status) {
        switch (status) {
        
		/*
		 * case VERIFIED: return "Verification completed successfully.";
		 */
        
            case PENDING:
                return "Verification is still in progress.";
            case APPROVED:
                return "Verification approved. All requirements met.";
            case REJECTED:
                return "Verification failed due to insufficient or incorrect information.";
            default:
                return "Unknown verification status.";
        }
    }

}
