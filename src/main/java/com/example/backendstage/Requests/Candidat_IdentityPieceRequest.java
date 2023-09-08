package com.example.backendstage.Requests;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Candidat_IdentityPieceRequest {


    private String etat;
    private String code;
    private boolean delivered;

    private Date date_validite;
    private Date delivery_date;
}
