package com.example.backendstage.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "fonctions")
@Data
public class Fonction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nom_fonction;
    @JsonIgnore
    @ManyToMany(mappedBy = "fonctions")
    private Set<Candidat> candidatSet = new HashSet<>();




}
