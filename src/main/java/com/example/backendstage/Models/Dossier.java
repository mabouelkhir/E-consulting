package com.example.backendstage.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "dossiers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @OneToMany(mappedBy = "dossier")
    @JsonManagedReference
    private List<DossierPieces> dossierPieces;





}
