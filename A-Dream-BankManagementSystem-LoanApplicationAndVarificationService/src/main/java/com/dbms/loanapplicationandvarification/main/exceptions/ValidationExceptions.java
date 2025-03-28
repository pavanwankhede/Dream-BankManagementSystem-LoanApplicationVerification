package com.dbms.loanapplicationandvarification.main.exceptions;

import java.util.Map;

public class ValidationExceptions extends RuntimeException {

	 private final Map<String, String> errors;

	    public ValidationExceptions(Map<String, String> errors) {
	        super("Validation failed");
	        this.errors = errors;
	    }

	    public Map<String, String> getErrors() {
	        return errors;
	    }
   
}
