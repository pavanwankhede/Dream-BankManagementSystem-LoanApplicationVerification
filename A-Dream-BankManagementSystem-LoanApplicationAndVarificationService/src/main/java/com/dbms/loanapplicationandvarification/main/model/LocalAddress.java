package com.dbms.loanapplicationandvarification.main.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "localAddressTable")
@Data
@Entity
public class LocalAddress {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
	 
	  @NotNull(message = "Street must not be null.")
	    private String street;

	    @NotNull(message = "City must not be null.")
	    private String city;

	    @NotNull(message = "State must not be null.")
	    private String state;

	    @NotNull(message = "Pin code must not be null.")
	    @Pattern(regexp = "^\\d{6}$", message = "Pin code must be exactly 6 digits.")
	    private String pinCode;  // Assuming 6-digit pin code format (India)

}
