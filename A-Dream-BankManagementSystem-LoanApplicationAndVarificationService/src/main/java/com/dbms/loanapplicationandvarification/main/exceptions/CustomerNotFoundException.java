package com.dbms.loanapplicationandvarification.main.exceptions;


public class CustomerNotFoundException extends RuntimeException{

	public CustomerNotFoundException(String message)
	{
		super(message);
	}
}
