package com.example.backendstage.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "fonctions")
@Data
public class Fonction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column
    private EFonction nom_fonction;

    @ManyToOne
    @JoinColumn(name = "candidat_id")
    private Candidat candidat;

    public Fonction(EFonction nom_fonction, Candidat candidat) {
        this.nom_fonction = nom_fonction;
        this.candidat = candidat;
    }

    public Fonction() {
    }
}
