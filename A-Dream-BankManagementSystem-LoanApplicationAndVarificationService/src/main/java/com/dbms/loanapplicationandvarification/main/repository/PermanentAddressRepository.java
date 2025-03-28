package com.dbms.loanapplicationandvarification.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbms.loanapplicationandvarification.main.model.PermanentAddress;


@Repository
public interface PermanentAddressRepository extends JpaRepository<PermanentAddress, Integer> {

}
