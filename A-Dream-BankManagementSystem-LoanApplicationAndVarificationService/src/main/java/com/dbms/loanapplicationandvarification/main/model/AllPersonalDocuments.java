package com.dbms.loanapplicationandvarification.main.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "PersonalDocumentsTable")
@Entity
public class AllPersonalDocuments {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Lob
    private byte[] addressProof; // Stores the image as binary data
	@Lob
    private byte[] panCard;       // Stores the image as binary data
	@Lob
    private byte[] aadharCard;    // Stores the image as binary data
	@Lob
    private byte[] incomeTaxCertificate; // Stores the image as binary data
	@Lob 
    private byte[] passportPhoto;    // Stores the image as binary data
	@Lob
    private byte[] salarySlip;         // Stores the image as binary data
	@Lob
    private byte[] signaturePhoto;           // Stores the image as binary data


}
