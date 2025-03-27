package com.dbms.loanapplicationandvarification.main.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "CoustomerAddressTable")
@Entity
public class CustomerAddress {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int customerAddressId;
	
	@OneToOne(cascade = CascadeType.ALL)
    private PermanentAddress permanentAddress;

    @OneToOne(cascade = CascadeType.ALL)
    private LocalAddress localAddress;

	 
}
