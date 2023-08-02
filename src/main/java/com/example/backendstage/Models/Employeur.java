package com.example.backendstage.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "employeurs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Employeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private String nom;
    @Column
    private String prenom;

    @Column
    private String num_tel;

    @Column
    private String adresse;
    @Column
    private String email;
    @Column
    private String code_emp;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "employeur")
    @JsonManagedReference
    private List<Candidat> candidatList;

    @Column
    private String city;
    @Column(length = 500000)
    @Lob
    private byte[] image;

}
