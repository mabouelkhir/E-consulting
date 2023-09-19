package com.example.backendstage.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "admins")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @Column
    private Date createdAt;

    @Column(length = 500000)
    @Lob
    private byte[] image;


}