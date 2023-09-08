package com.example.backendstage.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Candidat_IdentityPieces {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String etat;
    private String code;
    private boolean delivered;

    private Date date_validite;
    private Date delivery_date;


    @ManyToOne
    @JsonBackReference
    private Candidat candidat;

    @ManyToOne
    private Identity_piece identityPiece;
}
