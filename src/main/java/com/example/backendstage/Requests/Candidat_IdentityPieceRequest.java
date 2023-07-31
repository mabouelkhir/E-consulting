package com.example.backendstage.Requests;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Candidat_IdentityPieceRequest {

    private String etat;
    private String code;
    private LocalDateTime date_validite;
    private LocalDateTime created_at;
}
