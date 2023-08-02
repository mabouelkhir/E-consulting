package com.example.backendstage.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "fonctions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Fonction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nom_fonction;
    @JsonIgnore
    @ManyToMany(mappedBy = "fonctions" ,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Candidat> candidatSet ;




}
