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
@Table(name = "DependentInfoTable")
@Entity
public class DependentInfo {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
      
	    private int noOfFamilyMembers;
	    
	    @NotNull(message = "Marital status must not be null.")
	    @Pattern(regexp = "^(Single|Married|Divorced)$", message = "Marital status must be 'Single', 'Married', or 'Divorced'.")
	    private String maritalStatus;  // e.g., "single", "married", "divorced"
	    
	    private int noOfChildren;
	    
	    private int dependentMembers;
	    
	    @NotNull(message = "Family income must not be null.")
	    private double familyIncome;

}
