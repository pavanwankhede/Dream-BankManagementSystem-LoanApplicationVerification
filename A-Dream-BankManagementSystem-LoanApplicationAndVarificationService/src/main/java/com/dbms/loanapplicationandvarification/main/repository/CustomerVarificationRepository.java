package com.dbms.loanapplicationandvarification.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dbms.loanapplicationandvarification.main.model.CustomerVerification;

import jakarta.transaction.Transactional;

@Repository
public interface CustomerVarificationRepository extends JpaRepository<CustomerVerification, Integer> {
	


}
