package com.example.backendstage.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class DossierPieces {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Dossier dossier;

    @ManyToOne
    private Piece piece;
    private boolean delivered;
    private LocalDate deliveryDate;
    private String note;

}
