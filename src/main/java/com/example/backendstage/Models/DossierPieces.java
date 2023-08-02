package com.example.backendstage.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DossierPieces {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private Dossier dossier;

    @ManyToOne
    @JsonBackReference
    private Piece piece;
    private boolean delivered;
    private LocalDate deliveryDate;
    private String note;

}
