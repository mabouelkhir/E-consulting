package com.example.backendstage.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "operateurs")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})

public class Operateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private String nom;
    @Column
    private String prenom;
    @Column
    private String sexe;
    @Column
    private String email;
    @Column
    private String tel;
    @Column
    private String adresse;
    @Column
    private String date_naissance;
    @Column
    private  String cin;
    @Lob
    private byte[] image;
}
