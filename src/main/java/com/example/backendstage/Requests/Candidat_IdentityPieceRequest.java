package com.example.backendstage.Requests;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Candidat_IdentityPieceRequest {


    private String etat;
    private String code;
    private LocalDateTime date_validite;
    private LocalDateTime created_at;
}
