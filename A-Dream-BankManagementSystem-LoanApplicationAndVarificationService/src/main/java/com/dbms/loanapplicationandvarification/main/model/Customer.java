package com.dbms.loanapplicationandvarification.main.model;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "CustomerTable")
@Entity
public class Customer {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @NotNull(message = "You Can Not Take The Customer FirstName Is Null !!!")
	@NotBlank(message = "Customer FirstName Can Not Be Blank")
	 @Size(min = 3, max = 30, message = "Customer FirstName Must Be Between 3 And 15 Characters")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Customer FirstName Must Contain Only Letters And Spaces")
    private String firstName;
    
    @NotNull(message = "You Can Not Take The Customer lastName Is Null !!!")
   	@NotBlank(message = "Customer lastName Can Not Be Blank")
   	 @Size(min = 3, max = 30, message = "Customer lastName Must Be Between 3 And 15 Characters")
       @Pattern(regexp = "^[A-Za-z ]+$", message = "Customer lastName Must Contain Only Letters And Spaces")
    private String lastName;
 
    @NotNull(message = "Date of Birth must not be null.")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date of Birth must be in the format yyyy-MM-dd.")
    private String dateOfBirth;
    
    @Min(value = 18, message = "Age Must Be At Least 18/ year. Please Enter Valid Age")
	 @Max(value = 60, message = "Age Must be At Most 60/ year. Please Enter Valid Age")
    private int age;
    
    @NotNull(message = "Tenure in years must not be null.")
    private int tenureInYears;
    
    @NotBlank(message = "Gender is required")
    @Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be Male, Female,Other")
    private String gender;

    @Min(value = 1000000000L, message = "Enter a valid contact number")
	@Max(value = 9999999999L, message = "Enter a valid contact number")
    private String mobileNo;

    @Min(value = 1000000000L, message = "Enter a valid 10-digit contact number")
    @Max(value = 9999999999L, message = "Enter a valid 10-digit contact number")
    private String alternativeMobileNo;

    @NotNull(message = "Total loan required must not be null.")
    @Min(value = 1000, message = "Total loan required must be at least 1000.")
    @Max(value = 1000000, message = "Total loan required must not exceed 1,000,000.")
    private double totalLoanRequired;

    @Email(message = "Invalid email format")
	 @NotBlank(message = "Email is required")
    private String emailId;

    @OneToOne(cascade = CascadeType.ALL)
    private CustomerAddress customerAddress;

    @OneToOne(cascade = CascadeType.ALL)
    private AccountDetails accountDetails;

    @OneToOne(cascade = CascadeType.ALL)
    private CibilScore cibilScore;

    @OneToOne(cascade = CascadeType.ALL)
    private GuarantorDetails guarantorDetails;

    @OneToOne(cascade = CascadeType.ALL)
    private CustomerVerification customerVerification;

    @OneToOne(cascade = CascadeType.ALL)
    private DependentInfo dependentInfo;

    @OneToOne(cascade = CascadeType.ALL)
    private AllPersonalDocuments allPersonalDocuments;
	
		
	}
    


