package com.example.backendstage.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Data
public class Candidat_IdentityPieces {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String etat;
    private String code;
    private LocalDateTime date_validite;
    private LocalDateTime created_at;


    @ManyToOne
    private Candidat candidat;

    @ManyToOne
    private Identity_piece identityPiece;
}
