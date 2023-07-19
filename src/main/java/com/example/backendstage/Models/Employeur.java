package com.example.backendstage.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "employeurs")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Employeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String num_tel;

    @Column
    private String adresse;
    @Column
    private String email;
    @Column
    private String code_emp;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "employeur")
    private List<Candidat> candidatList;

    @Column
    private String city;

}
