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
@Table(name = "CoustomerVarificationTable")
@Entity
public class CustomerVerification {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	 @NotNull(message = "Date must not be null.")
	    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date must be in the format yyyy-MM-dd.")
	    private String date;  // Date format: yyyy-MM-dd

	    @NotNull(message = "Verification status must not be null.")
	    @Size(min = 3, max = 20, message = "Verification status must be between 3 and 20 characters.")
	    private String verificationStatus;

	    @Size(max = 255, message = "Remark must be at most 255 characters.")
	    private String remark;

		/*
		 * @OneToOne(cascade = CascadeType.ALL) private AllPersonalDocuments
		 * allPersonalDocuments;
		 */

}
