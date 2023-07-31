package com.example.backendstage.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "admins")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
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
    @Column(length = 500000)
    @Lob
    private byte[] image;


}