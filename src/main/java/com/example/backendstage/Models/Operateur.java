package com.example.backendstage.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "operateurs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private EStatus status;
    @Column
    private Timestamp date_naissance;
    @Column
    private  String cin;
    @Column(length = 500000)
    @Lob
    private byte[] image;

}
