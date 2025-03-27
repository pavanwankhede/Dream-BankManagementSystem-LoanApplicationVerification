package com.dbms.loanapplicationandvarification.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbms.loanapplicationandvarification.main.model.AllPersonalDocuments;

@Repository
public interface AllPersonalDocumentsRepository extends JpaRepository<AllPersonalDocuments, Integer> {

}
