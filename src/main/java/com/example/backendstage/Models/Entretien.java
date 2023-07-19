package com.example.backendstage.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "entretiens")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Entretien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String titre;
    @Column
    private String description;

    @Column
    private LocalDateTime date_entretien;

    @Column
    private String status;

    @ManyToOne
    @JoinColumn(name = "candidat_id")
    private Candidat candidat;
}
