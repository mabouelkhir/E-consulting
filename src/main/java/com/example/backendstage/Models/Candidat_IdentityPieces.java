package com.example.backendstage.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private LocalDateTime date_validite;
    private LocalDateTime created_at;


    @ManyToOne
    @JsonBackReference
    private Candidat candidat;

    @ManyToOne
    @JsonBackReference
    private Identity_piece identityPiece;
}
