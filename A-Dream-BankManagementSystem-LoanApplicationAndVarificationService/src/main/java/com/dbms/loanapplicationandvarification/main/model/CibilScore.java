package com.dbms.loanapplicationandvarification.main.model;


import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.dbms.loanapplicationandvarification.main.enums.ScoreCategories;
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "CibilScoreTable")
@Entity
public class CibilScore {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int cibilId;
	private long cibilScore;
	private String remarks;
	
	@Enumerated(EnumType.STRING)
	private ScoreCategories scoreCategories;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Temporal(TemporalType.TIME)
	private Time time;
	   
	 @PrePersist
	    protected void onCreate() {
	        this.date = Date.valueOf(LocalDate.now()); // Set the current date
	        this.time = Time.valueOf(LocalTime.now()); // Set the current time
	    }

}
