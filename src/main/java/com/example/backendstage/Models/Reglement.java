package com.example.backendstage.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Table(name = "reglements")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})

public class Reglement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidat_id")
    private Candidat candidat;

    private LocalDateTime date_reglement;

    private String montant;



}
