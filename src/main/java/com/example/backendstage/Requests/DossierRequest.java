package com.example.backendstage.Requests;

import com.example.backendstage.Models.Candidat;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DossierRequest {
    private String numeroDossier;

    private String status;
    private String note;



}
