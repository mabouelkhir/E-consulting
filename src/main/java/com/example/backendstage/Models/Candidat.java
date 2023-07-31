package com.example.backendstage.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Date date_naissance;
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
    @Column(length = 500000)
    @Lob
    private byte[] image;
    @ManyToMany
    @JoinTable(
            name = "candidat_fonctions",
            joinColumns = @JoinColumn(name = "candidat_id"),
            inverseJoinColumns = @JoinColumn(name = "fonction_id"))
    private Set<Fonction> fonctions = new HashSet<>();

    @Column
    private String obs;
    @JsonIgnore
    @OneToMany(mappedBy = "candidat")
    private List<Candidat_IdentityPieces> candidatIdentityPieces;
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
    @Lob
    @Column
    private byte[] cv;
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
