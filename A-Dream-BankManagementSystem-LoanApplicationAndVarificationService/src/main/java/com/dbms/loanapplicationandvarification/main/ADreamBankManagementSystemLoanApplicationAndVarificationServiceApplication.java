package com.dbms.loanapplicationandvarification.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

@EntityScan
@EnableDiscoveryClient
@SpringBootApplication
public class ADreamBankManagementSystemLoanApplicationAndVarificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ADreamBankManagementSystemLoanApplicationAndVarificationServiceApplication.class, args);
	}

	@Bean
	public ObjectMapper mapper() {
		
		return new ObjectMapper();
	}
}
