package com.dbms.loanapplicationandvarification.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dbms.loanapplicationandvarification.main.enums.VerificationStatus;
import com.dbms.loanapplicationandvarification.main.model.Customer;

import jakarta.transaction.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {


	/*
	 * @Modifying
	 * 
	 * @Transactional
	 * 
	 * @Query("DELETE FROM Customer c WHERE c.customerId = :customerId AND c.customerVerification.verificationStatus = :status"
	 * ) int deleteRejectedCustomer(@Param("customerId") int
	 * customerId, @Param("status") VerificationStatus status);
	 */
	@Modifying
	@Transactional
	@Query("DELETE FROM Customer c WHERE c.customerId = :customerId")
	int deleteCustomerAndRelatedData(@Param("customerId") int customerId);

}
	

