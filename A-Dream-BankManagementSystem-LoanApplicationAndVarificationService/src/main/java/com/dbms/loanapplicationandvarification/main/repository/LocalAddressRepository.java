package com.dbms.loanapplicationandvarification.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbms.loanapplicationandvarification.main.model.LocalAddress;

@Repository
public interface LocalAddressRepository extends JpaRepository<LocalAddress, Integer> {

}
