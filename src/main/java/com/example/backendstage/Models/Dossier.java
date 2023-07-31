package com.example.backendstage.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "dossiers")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})

public class Dossier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String numeroDossier;

    @Column
    private LocalDateTime date_creation;

    @OneToOne(fetch = FetchType.LAZY)
    private Candidat candidat;
    @Column
    private String status;
    @Column
    private String note;
    @JsonIgnore
    @OneToMany(mappedBy = "dossier")
    private List<DossierPieces> dossierPieces;





}
