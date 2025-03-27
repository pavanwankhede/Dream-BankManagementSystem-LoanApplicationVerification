package com.dbms.loanapplicationandvarification.main.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponseDTO {
    
    private String message;
    private String date;
    private String time;

    public ErrorResponseDTO(String message) {
        this.message = message;
        this.date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        this.time = new SimpleDateFormat("HH:mm:ss").format(new Date());
    }
}