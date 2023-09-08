package com.example.backendstage.Requests;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CinRequest {
    private String code;

    private Date date_naissance;
    private Date date_validite;


}
