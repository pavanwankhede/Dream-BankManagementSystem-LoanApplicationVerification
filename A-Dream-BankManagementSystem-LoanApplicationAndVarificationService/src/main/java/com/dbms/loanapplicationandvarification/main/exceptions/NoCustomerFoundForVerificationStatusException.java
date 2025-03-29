package com.dbms.loanapplicationandvarification.main.exceptions;

public class NoCustomerFoundForVerificationStatusException extends RuntimeException{
public NoCustomerFoundForVerificationStatusException(String message)
{
	super (message);
}
}
