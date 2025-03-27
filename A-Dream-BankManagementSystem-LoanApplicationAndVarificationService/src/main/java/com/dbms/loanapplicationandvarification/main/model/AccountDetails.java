package com.dbms.loanapplicationandvarification.main.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "AccountDetailsTable")
@Entity
public class AccountDetails {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 @NotNull(message = "Account holder name must not be null.")
	    private String accountHolderName;

	    @NotNull(message = "Account number must not be null.")
	    @Pattern(regexp = "^[0-9]{12}$", message = "Account number must be exactly 12 digits.")
	    private String accountNumber;
	    
	    @NotNull(message = "Account type must not be null.")
	    @Size(min = 3, max = 50, message = "Account type must be between 3 and 50 characters.")
	    @Pattern(regexp = "^(Savings|Current|Deposit|Fixed deposit)$", message = "Account type must be either 'savings', 'current', 'deposit', or 'fixed deposit'.")
	    private String accountType;
	  
	}



