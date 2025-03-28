package com.dbms.loanapplicationandvarification.main.model;

import java.sql.Time;
import java.util.Date;

import com.dbms.loanapplicationandvarification.main.enums.VerificationStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
			 
	   @Enumerated(EnumType.STRING)  // Ensure Enum is stored as String
	    @NotNull(message = "Verification status must not be null.")
	 private VerificationStatus verificationStatus;

	    @Size(max = 255, message = "Remark must be at most 255 characters.")
	    private String remark;
	    
		 @Temporal(TemporalType.DATE)
			private Date verificationDate;
			
		 @Temporal(TemporalType.TIME)
			private Time verificationTime;
	   
	    
		/*
		 * @OneToOne(cascade = CascadeType.ALL) private AllPersonalDocuments
		 * allPersonalDocuments;
		 */

}
