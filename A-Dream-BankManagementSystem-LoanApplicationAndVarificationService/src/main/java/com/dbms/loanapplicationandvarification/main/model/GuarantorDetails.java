package com.dbms.loanapplicationandvarification.main.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "GuarantorDetailsTable")
@Entity
public class GuarantorDetails {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 @NotNull(message = "Full name must not be null.")
	    @Size(min = 1, max = 100, message = "Full name must be between 1 and 100 characters.")
	    private String fullName;

	    @NotNull(message = "Guarantor relation must not be null.")
	    @Size(min = 3, max = 50, message = "Guarantor relation must be between 3 and 50 characters.")
	    private String guarantorRelation;

	    @NotNull(message = "Address must not be null.")
	    private String address;

	    @NotNull(message = "Date of birth must not be null.")
	    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date of birth must be in the format yyyy-MM-dd.")
	    private String dateOfBirth;  // Format: yyyy-MM-dd

	    @NotNull(message = "Aadhar number must not be null.")
	    @Pattern(regexp = "^\\d{12}$", message = "Aadhar number must be exactly 12 digits.")
	    private String aadharNo;

	    @NotNull(message = "Job details must not be null.")
	    private String jobDetails;

	    @NotNull(message = "Local address must not be null.")
	    private String localAddress;

	    @NotNull(message = "Permanent address must not be null.")
	    private String permanentAddress;

	    @NotNull(message = "Email must not be null.")
	    @Email(message = "Email should be valid.")
	    private String emailId;

	    @Min(value = 1000000000L, message = "Enter a valid contact number")
		@Max(value = 9999999999L, message = "Enter a valid contact number")
	    private String contactNo;


}
