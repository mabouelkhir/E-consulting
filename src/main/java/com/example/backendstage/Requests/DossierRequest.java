package com.example.backendstage.Requests;

import com.example.backendstage.Models.Candidat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DossierRequest {
    private String numeroDossier;

    private LocalDateTime date_creation;
    private String status;
    private String note;



}
