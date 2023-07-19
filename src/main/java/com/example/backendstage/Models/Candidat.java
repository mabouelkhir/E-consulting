package com.example.backendstage.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "candidats")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})

public class Candidat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String nom;
    @Column
    private String prenom;
    @Column
    private String date_naissance;
    @Column
    private String sexe;
    @Column
    private String adresse;
    @Column
    private String num_tel;
    @Column
    private String email;
    @Column
    private  String cin;
    @Lob
    private byte[] image;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "candidat")
    private List<Fonction> fonctions;
    @Column
    private String obs;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "candidat")
    private List<Identity_piece> identity_pieces;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "candidat")
    private List<Entretien> entretiens;

    @Column
    private String tl;

    @Column
    private String groupe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id")
    private Agent agent;

    private String status;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "candidat")
    private List<Reglement> reglements;

    @Column
    private String situation_fam;

    @Column
    private int children;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeur_id")
    private Employeur employeur;






}
