package com.dbms.loanapplicationandvarification.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbms.loanapplicationandvarification.main.model.CustomerAddress;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Integer> {

}
