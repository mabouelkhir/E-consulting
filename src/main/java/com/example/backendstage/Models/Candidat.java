package com.example.backendstage.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "candidats")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private LocalDateTime createdAt;
    @Column
    private String adresse;
    @Column
    private String num_tel;
    @Column
    private String email;

    @Column(length = 500000)
    @Lob
    private byte[] image;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "candidat_fonctions",
            joinColumns ={ @JoinColumn(name = "candidat_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "fonction_id",referencedColumnName = "id")})
    private Set<Fonction> fonctions ;

    @Column
    private String obs;
    @OneToMany(mappedBy = "candidat")
    @JsonManagedReference
    private List<Candidat_IdentityPieces> candidatIdentityPieces;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "candidat")
    @JsonManagedReference
    private List<Entretien> entretiens;

    @Column
    private String tl;

    @Column
    private String groupe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id")
    private Agent agent;

    private EStatus status;
    @Lob
    @Column(length = 500000)
    private byte[] cv;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "candidat")
    @JsonManagedReference
    private List<Reglement> reglements;

    @Column
    private String situation_fam;

    @Column
    private int children;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "employeur_id")
    private Employeur employeur;






}
