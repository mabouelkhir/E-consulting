package com.example.backendstage.Requests;

import com.example.backendstage.Models.EStatus;
import com.example.backendstage.Models.Fonction;
import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidatRequest {
    private String adresse;
    private int children;
    private Date date_naissance;
    private String num_tel;
    private String obs;
    private String sexe;
    private String situation_fam;
    private EStatus status;
    private String tl;
    private String Ref_contrat;
    private String id_Employeur;

    private boolean visaRecu;
   private boolean tlsRecu;

    private boolean ofiiRecu;




}

